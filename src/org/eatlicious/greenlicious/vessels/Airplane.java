package org.eatlicious.greenlicious.vessels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Unit;

public class Airplane implements Vessel {

	enum HaulDistance{
		SHORT(0, 1000), MEDIUM(1001, 3700), LONG(3701, Integer.MAX_VALUE);
		
		private Integer minDistance;
		private Integer maxDistance;
		
		private HaulDistance(int minDistance, int maxDistance){
			this.minDistance = minDistance;
			this.maxDistance = maxDistance;
		}
		
		public Integer getMinDistance(){
			return this.minDistance;
		}
		
		public Integer getMaxDistance(){
			return this.maxDistance;
		}
	}
	
	// TODO make set of conversionfactors for co2e in other place
	private static double KEROSENE_TO_CO2E_FACTOR = 3.128;
	private static int WEIGHT_PER_PASSENGER = 100;
	
	private static Map<HaulDistance, Double> freightCapacityUtilization;
	
	private static Map<HaulDistance, Double> passengerCapacityUtilization;
	
	static {
		// values taken from EcoTransit (2013)
		freightCapacityUtilization.put(HaulDistance.SHORT, 0.55);
		freightCapacityUtilization.put(HaulDistance.MEDIUM, 0.6);
		freightCapacityUtilization.put(HaulDistance.LONG, 0.65);
		passengerCapacityUtilization.put(HaulDistance.SHORT, 0.65);
		passengerCapacityUtilization.put(HaulDistance.MEDIUM, 0.7);
		passengerCapacityUtilization.put(HaulDistance.LONG, 0.8);
	}
	
	private AirplaneSpecification specs;

	public Airplane(AirplaneSpecification specs) {
		this.specs = specs;
	}

	public Quantity getTotalCO2e(Integer distance, boolean useRFI) {
		// TODO check if flight distance should be adjusted in here or if this is done by caller
		Double fuelConsumption = this.getFuelConsumption(distance);
		if (useRFI) {
			// multiply with rfi factor
			fuelConsumption *= RFICalculator.getRFIFactor(distance);
		}
		// multiply with kerosene factor
		double co2e = fuelConsumption * KEROSENE_TO_CO2E_FACTOR;
		// return result
		return new QuantityImpl(co2e, Unit.CO2E);
	}

	public Quantity getTotalCO2ePerKM(Integer distance, boolean useRFI) {
		Quantity co2e = this.getTotalCO2e(distance, useRFI);
		co2e.setAmount(co2e.getAmount() / distance);
		return co2e;
	}

	public Quantity getCO2e(Integer distance, Quantity payload, boolean useRFI) {
		Double co2e = this.getTotalCO2e(distance, useRFI).getAmount();
		// calc co2e allocated to freight
		co2e = co2e / this.getTransportedWeight(distance) * payload.convert(Unit.KILOGRAM).getAmount();
		return new QuantityImpl(co2e, Unit.CO2E);
	}

	public Quantity getCO2ePerKM(Integer distance, Quantity payload, boolean useRFI) {
		Quantity co2e = this.getCO2e(distance, payload, useRFI);
		co2e.setAmount(co2e.getAmount() / distance);
		return co2e;
	}

	private double getTransportedWeight(Integer distance) {
		HaulDistance hd = this.getHaulDistance(distance);
		double freightWeight = this.specs.getMaxPayload() * freightCapacityUtilization.get(hd);
		double passengerWeight = this.specs.getSeats() * passengerCapacityUtilization.get(hd) * WEIGHT_PER_PASSENGER;
		return freightWeight + passengerWeight;
	}
	
	private HaulDistance getHaulDistance(Integer distance){
		if(distance < 0){
			throw new IllegalArgumentException("Distance has to be bigger than 0, was " + distance);
		}
		
		HaulDistance result = null;
		for(HaulDistance dist : HaulDistance.values()){
			if(dist.minDistance < distance && dist.maxDistance > distance){
				result = dist;
				break;
			}
		}
		return result;
	}

	/**
	 * This method calculates the fuel consumption of this Airplane based on the distance traveled.
	 * 
	 * @param distance The total distance traveled for the transport
	 * @return The amount of fuel burned by this Airplane when traveling the given distance.
	 */
	public Double getFuelConsumption(Integer distance) {
		Double result = null;
		
		// calculate with multiple trips if distance exceeds the max range of this airplane
		double numTrips = Math.ceil(distance.doubleValue() / this.specs.getMaxRange());
		double calcDist = distance / numTrips;

		if (this.specs.hasConsumptionProfile()) {
			Map<Integer, Double> consumptionProfile = this.specs.getConsumptionProfile();
			if (consumptionProfile.containsKey(calcDist)) {
				result = consumptionProfile.get(calcDist);
			} else {
				Integer minKey = null;
				Integer maxKey = null;
				// make List from keyset
				List<Integer> keys = new ArrayList<Integer>(consumptionProfile.keySet());
				// sort keys in ascending order
				Collections.sort(keys);
				// identify closest values
				for (Integer key : keys) {
					if (calcDist < key) {
						// once a key with a higher distance is found the maxValue is found. exit loop.
						maxKey = key;
						break;
					} else {
						// as long as distance is bigger than key a new minValue is found
						minKey = key;
					}
				}
				if (minKey == null) {
					// may happen if consumption profile holds only key-values bigger than distance
					// closest value will be used
					result = consumptionProfile.get(maxKey);
				} else if (maxKey == null) {
					// may happen if consumption profile hold only key-values smaller than distance
					// closest value will be used
					result = consumptionProfile.get(minKey);
				} else {
					// linear interpolation
					double steep = (consumptionProfile.get(maxKey) - consumptionProfile.get(minKey))
							/ (maxKey - minKey);
					result = consumptionProfile.get(minKey) + (calcDist - consumptionProfile.get(minKey)) * steep;
				}
			}
		}
		return result * numTrips;
	}

}
