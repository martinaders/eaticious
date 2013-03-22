package org.eaticious.greenlicious.vessels;

import org.eaticious.common.FoodProduct.TransportClass;
import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Unit;
import org.eaticious.greenlicious.RegionSpecification.Landscape;
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
