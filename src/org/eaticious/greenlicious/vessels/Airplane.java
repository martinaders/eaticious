package org.eaticious.greenlicious.vessels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Unit;
import org.eaticious.greenlicious.calc.RFICalculator;
import org.eaticious.greenlicious.vessels.AirplaneSpecification.StandardModel;

public class Airplane implements Vessel {

	enum HaulDistance {
		SHORT(0, 1000), MEDIUM(1000, 3700), LONG(3700, Integer.MAX_VALUE);

		private Integer minDistance;
		private Integer maxDistance;

		private HaulDistance(int minDistance, int maxDistance) {
			this.minDistance = minDistance;
			this.maxDistance = maxDistance;
		}

		public Integer getMinDistance() {
			return this.minDistance;
		}

		public Integer getMaxDistance() {
			return this.maxDistance;
		}
	}

	// TODO make set of conversion factors for co2e in other place, maybe enum or DB
	public static double KEROSENE_TO_CO2E_FACTOR = 3.128;
	public static int WEIGHT_PER_PASSENGER = 100;

	private Map<HaulDistance, Double> freightCapacityUtilization;

	private Map<HaulDistance, Double> passengerCapacityUtilization;

	/**
	 * AirplaneSpecification holding data needed to make CO2e calculations for Airplanes
	 */
	private AirplaneSpecification specs;

	/**
	 * Standard constructor
	 * 
	 * @param specs
	 *            The AirplaneSpecification of the new Airplane
	 */
	public Airplane(AirplaneSpecification specs) {
		// values from EcoTransIT
		// TODO might have to move this to calc class or to DB, was static but now is in constructor to avoid conflicts in UnitTests
		this.freightCapacityUtilization = new HashMap<Airplane.HaulDistance, Double>();
		this.freightCapacityUtilization.put(HaulDistance.SHORT, 0.55);
		this.freightCapacityUtilization.put(HaulDistance.MEDIUM, 0.6);
		this.freightCapacityUtilization.put(HaulDistance.LONG, 0.65);
		this.passengerCapacityUtilization = new HashMap<Airplane.HaulDistance, Double>();
		this.passengerCapacityUtilization.put(HaulDistance.SHORT, 0.65);
		this.passengerCapacityUtilization.put(HaulDistance.MEDIUM, 0.7);
		this.passengerCapacityUtilization.put(HaulDistance.LONG, 0.8);

		this.specs = specs;
	}

	/**
	 * Standard constructor
	 * 
	 * @param model
	 *            The StandardModel to be constructed
	 */
	public Airplane(StandardModel model) {
		this(new AirplaneSpecification(model));
	}

	/**
	 * Calculates the complete CO2e emission for the whole flight
	 * 
	 * @param distance
	 *            the distance traveled
	 * @param useRFI
	 *            true if the higher greenhouse potential for emission near the stratosphere should be used, false
	 *            otherwise
	 * @return The CO2e emission for the whole flight
	 */
	public Quantity getTotalCO2e(Double distance, boolean useRFI) {
		// TODO check if flight distance should be adjusted in here or if this is done by caller
		Double fuelConsumption = this.getFuelConsumption(distance).getAmount();
		if (useRFI) {
			// multiply with rfi factor
			fuelConsumption *= RFICalculator.getRFIFactor(distance);
		}
		// multiply with kerosene factor
		double co2e = fuelConsumption * KEROSENE_TO_CO2E_FACTOR;
		// return result
		return new QuantityImpl(co2e, Unit.CO2E);
	}

	/**
	 * Calculates the CO2e emission per kilometer for a flight
	 * 
	 * @param distance
	 *            The distance traveled
	 * @param useRFI
	 *            true if the higher greenhouse potential for emission near the stratosphere should be used, false
	 *            otherwise
	 * @return The CO2e emission per kilometer for the whole flight
	 */
	public Quantity getTotalCO2ePerKM(Double distance, boolean useRFI) {
		Quantity co2e = this.getTotalCO2e(distance, useRFI);
		co2e.setAmount(co2e.getAmount() / distance);
		return co2e;
	}

	/**
	 * Calculates the CO2e emission allocated to a particular part of the payload for the whole flight
	 * 
	 * @param distance
	 *            The distance traveled
	 * @param payload
	 *            the weight for which the allocation will be made (any Quantity of PhysicalDimension.MASS can be used)
	 * @param useRFI
	 *            true if the higher greenhouse potential for emission near the stratosphere should be used, false
	 *            otherwise
	 * @return The CO2e emission allocated to the weight of payload for the whole flight
	 */
	public Quantity getCO2e(Double distance, Quantity payload, boolean useRFI) {
		Double co2e = this.getTotalCO2e(distance, useRFI).getAmount();
		// calc co2e allocated to freight
		co2e = co2e / this.getTransportedWeight(distance) * payload.convert(Unit.KILOGRAM).getAmount();
		return new QuantityImpl(co2e, Unit.CO2E);
	}

	/**
	 * Calculates the CO2e emission per kilometer allocated to a particular part of the payload for the whole flight
	 * 
	 * @param distance
	 *            The distance traveled
	 * @param payload
	 *            the weight for which the allocation will be made (any Quantity of PhysicalDimension.MASS can be used)
	 * @param useRFI
	 *            true if the higher greenhouse potential for emission near the stratosphere should be used, false
	 *            otherwise
	 * @return The CO2e emission allocated to the weight of payload per kilometer
	 */
	public Quantity getCO2ePerKM(Double distance, Quantity payload, boolean useRFI) {
		Quantity co2e = this.getCO2e(distance, payload, useRFI);
		co2e.setAmount(co2e.getAmount() / distance);
		return co2e;
	}

	/**
	 * Calculates the average payload (transported weight) in kilogram using standard capacity utilization factors for
	 * freight and passengers
	 * 
	 * @param distance
	 *            The distance traveled
	 * @return The average payload in kilogram for flights over the given distance
	 */
	private double getTransportedWeight(Double distance) {
		HaulDistance hd = this.getHaulDistance(distance);
		double freightWeight = this.specs.getMaxPayload() * freightCapacityUtilization.get(hd);
		double passengerWeight = this.specs.getSeats() * passengerCapacityUtilization.get(hd) * WEIGHT_PER_PASSENGER;
		return freightWeight + passengerWeight;
	}

	/**
	 * Returns the HaulDistance (cluster of distances for freight transports) for a given distance
	 * 
	 * @param distance
	 *            the real distance traveled
	 * @return The HaulDistance which matches the real distance traveled
	 */
	private HaulDistance getHaulDistance(Double distance) {
		if (distance < 0) {
			throw new IllegalArgumentException("Distance has to be bigger than 0, was " + distance);
		}

		HaulDistance result = null;
		for (HaulDistance dist : HaulDistance.values()) {
			if (dist.minDistance <= distance && dist.maxDistance > distance) {
				result = dist;
				break;
			}
		}
		return result;
	}

	/**
	 * This method calculates the fuel consumption of this Airplane based on the distance traveled.
	 * 
	 * @param distance
	 *            The total distance traveled for the transport
	 * @return The amount of fuel burned by this Airplane when traveling the given distance.
	 */
	public Quantity getFuelConsumption(Double distance) {
		Double result = null;

		// calculate with multiple trips if distance exceeds the max range of this airplane
		double numTrips = Math.ceil(distance.doubleValue() / this.specs.getMaxRange());
		double calcDist = distance / numTrips;

		if (this.specs.hasConsumptionData()) {
			Map<Double, Double> consumptionProfile = this.specs.getConsumptionProfile();
			if (consumptionProfile.containsKey(calcDist)) {
				result = consumptionProfile.get(calcDist);
			} else {
				Double minKey = null;
				Double maxKey = null;
				// make List from keyset
				List<Double> keys = new ArrayList<Double>(consumptionProfile.keySet());
				// sort keys in ascending order
				Collections.sort(keys);
				// identify closest values
				for (Double key : keys) {
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
					// linear interpolation will be used
					result = calcDist * consumptionProfile.get(maxKey) / maxKey;
				} else if (maxKey == null) {
					// may happen if consumption profile hold only key-values smaller than distance
					// linear interpolation will be used
					result = calcDist * consumptionProfile.get(minKey) / minKey;
				} else {
					// linear interpolation
					double steep = (consumptionProfile.get(maxKey) - consumptionProfile.get(minKey))
							/ (maxKey - minKey);
					result = consumptionProfile.get(minKey) + (calcDist - minKey) * steep;
				}

			}
		}
		return new QuantityImpl(result * numTrips, Unit.KILOGRAM);
	}

}
