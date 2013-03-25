package org.eaticious.greenlicious.vessels;

import org.eaticious.common.FoodProduct.TransportClass;
import org.eaticious.common.RegionSpecification.Landscape;
import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Unit;
import org.eaticious.greenlicious.vessels.ElectricTrainSpecification.TrainModel;

public class ElectricTrain implements Vessel {

	/**
	 * The specification of the truck
	 */
	private ElectricTrainSpecification specs;

	public ElectricTrain(TrainModel model) {
		this(new ElectricTrainSpecification(model));
	}

	public ElectricTrain(ElectricTrainSpecification specs) {
		this.specs = specs;
	}

	public Quantity getFuelConsumption(final Landscape landscape, final TransportClass tc) {
		final Quantity result = new QuantityImpl();
		result.setUnit(Unit.KILOWATTHOUR);
		
		final double lsFactor = getLandscapeFactor(landscape);
		final double tcFactor = getTransportClassFactor(tc);
		
		final double value = 0.0012 * Math.pow(this.specs.getGTW(), -0.62) * lsFactor / tcFactor;
		result.setAmount(value);
		
		return result;
	}
	
	public Quantity getCO2ePerKGKM(Double energyMixFactor, Landscape landscape, TransportClass tc) {
		Double amount = this.getFuelConsumption(landscape, tc).getAmount() * energyMixFactor;
		return new QuantityImpl(amount, Unit.KG_CO2E);
	}
	
	public Quantity getCO2ePerKG(Quantity distance, Double energyMixFactor, Landscape landscape, TransportClass tc) {
		Double calcDist = distance.convert(Unit.KILOMETER).getAmount();
		Double amount = this.getFuelConsumption(landscape, tc).getAmount() * energyMixFactor * calcDist;
		return new QuantityImpl(amount, Unit.KG_CO2E);
	}
	
	public Quantity getCO2e(Quantity weight, Quantity distance, Double energyMixFactor, Landscape landscape,
			TransportClass tc) {
		Double calcDist = distance.convert(Unit.KILOMETER).getAmount();
		Double calcWeight = weight.convert(Unit.KILOGRAM).getAmount();
		Double amount = this.getFuelConsumption(landscape, tc).getAmount() * energyMixFactor * calcDist * calcWeight;
		return new QuantityImpl(amount, Unit.KG_CO2E);
	}

	private static double getLandscapeFactor(final Landscape landscape) {
		double result;
		switch (landscape) {
		case FLAT:
			result = 0.9;
			break;
		case MOUNTAIN:
			result = 1.1;
			break;
		case HILL:
		default:
			result = 1.0;
			break;
		}
		
		return result;
	}

	private static double getTransportClassFactor(final TransportClass tc) {
		double result;
		switch (tc) {
		case BULK:
			result = 0.6;
			break;
		case VOLUME:
			result = 0.4;
			break;
		case AVERAGE:
		case UNKNOWN:
		default:
			result = 0.52;
			break;
		}
		return result;
	}

	

	


}
