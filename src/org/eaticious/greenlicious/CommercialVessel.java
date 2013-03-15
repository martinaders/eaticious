package org.eaticious.greenlicious;

import org.eaticious.common.FoodProduct;
import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Region;
import org.eaticious.common.Unit;

/**
 * @author Sven Peetz
 * 
 */
public enum CommercialVessel {

	TRUCK, TRAIN_ELECTRIC, TRAIN_DIESEL, PLANE, SHIP_OCEAN, SHIP_BARGE;
	
	private int TRAIN_STD_GTW = 1500;
	private double TRAIN_LANDSCAPE_FLAT = 0.9;
	private double TRAIN_LANDSCAPE_HILLY = 0.9;
	private double TRAIN_LANDSCAPE_MOUNTAIN = 0.9;
	private double TRAIN_WEIGHTFACTOR_BULK = 0.6;
	private double TRAIN_WEIGHTFACTOR_AVG = 0.52;
	private double TRAIN_WEIGHTFACTOR_VOLUME = 0.4;
	
	

	/**
	 * This method calculates the base emissions in CO2e per kgkm for the
	 * transport
	 * 
	 * @param product
	 *            The product to be transported
	 * @param distance
	 * @param loadFactor
	 * @param emptyTripFactor
	 * @param transportingRegion
	 * @return
	 */
	public Quantity getCO2eBaseEmission(FoodProduct product, Integer distance,
			Double loadFactor, Double emptyTripFactor, Region transportingRegion) {
		Quantity result = null;
		switch (this) {
		case TRUCK:
			result = this.calculateTruckBaseEmissions();
			break;
		case TRAIN_ELECTRIC:
			result = this.calcTrainElectricBaseEmissions(product,
					transportingRegion);
			break;
		case SHIP_OCEAN:
			result = this.calculateShipBaseEmissions();
			break;
		case SHIP_BARGE:
			result = this.calculateBargeBaseEmissions();
			break;
		case PLANE:
			result = this.calculatePlaneBaseEmissions();
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * This method calculates the base emission of the transport in CO2e per
	 * kgkm and multiplies it with the distance and weight of the freight.
	 * 
	 * @param product
	 *            The product to be transported
	 * @param kg
	 *            The weight of the transported good in kg
	 * @param distance
	 *            the travelled distance for this transport
	 * @param loadFactor
	 *            The loadFactor of the transport
	 * @param emptyTripFactor
	 *            The empty trip factor of the transport
	 * @param transportingRegion
	 *            the region where the transport is taking place
	 * @return The total CO2e emission from the transportation
	 */
	public Quantity getCO2eTotalEmission(FoodProduct product, Double kg,
			Integer distance, Double loadFactor, Double emptyTripFactor,
			Region transportingRegion) {
		Quantity result = getCO2eBaseEmission(product, distance, loadFactor,
				emptyTripFactor, transportingRegion);

		result.setAmount(result.getAmount() * kg * distance);
		return result;
	}

	
	private Quantity calculateTruckBaseEmissions() {
		// TODO implement
		return null;
	}

	/**
	 * DISCUSS (Daniel) what should be assumed as transportingRegion is transport is passing
	 * borders? (the energymix of different countries is not equal)
	 * 
	 * Calculates the base emission in CO2e per kgkm for electric train
	 * transportation
	 * 
	 * @param product
	 *            The FoodProduct to be transported
	 * @param transportingRegion
	 *            The Region where the transportation is taking place
	 * @return The Quantity representing the CO2e emissions per kgkm
	 */
	private Quantity calcTrainElectricBaseEmissions(FoodProduct product,
			Region transportingRegion) {

		// set the gross ton weight (gtw) of the wagons, this is depending on
		// region, internationally 1000 can be used, some countries use bigger
		// wagons (USA, CANADA, etc...)
		// if no region-specific gtw-value is available gtw will be set to 1000
		int gtw = transportingRegion.getTransportationDetails().getTrainGTW();
		if (gtw == 0) {
			gtw = TRAIN_STD_GTW;
		}

		// determine factor for region specific landscape. If no landscape is
		// defined the factor will be set to 1 (average, hilly region)
		double landscapefactor;
		switch (transportingRegion.getLandscape()) {
		case FLAT:
			landscapefactor = TRAIN_LANDSCAPE_FLAT;
			break;
		case MOUNTAIN:
			landscapefactor = TRAIN_LANDSCAPE_MOUNTAIN;
			break;
		case HILL:
		default:
			landscapefactor = TRAIN_LANDSCAPE_HILLY;
			break;
		}

		// determine factor for weightclass (BULK, AVERAGE, VOLUME) of product
		// if no weightclass is set AVERAGE will be chosen
		double weightfactor;
		switch (product.getWeightClass()) {
		case VOLUME:
			weightfactor = TRAIN_WEIGHTFACTOR_VOLUME;
			break;
		case BULK:
			weightfactor = TRAIN_WEIGHTFACTOR_BULK;
			break;
		case AVERAGE:
		default:
			weightfactor = TRAIN_WEIGHTFACTOR_AVG;
			break;
		}

		// calculate the base emission per kgkm for this transport
		double co2eBaseValue = transportingRegion.getEnergyMixFactor() * 0.0012
				* Math.pow(gtw, -0.62) * landscapefactor / weightfactor;
		return new QuantityImpl(co2eBaseValue, Unit.CO2E);
	}

	private Quantity calculateShipBaseEmissions() {
		// TODO implement
		return null;
	}

	private Quantity calculateBargeBaseEmissions() {
		// TODO implement
		return null;
	}

	private Quantity calculatePlaneBaseEmissions() {
		// TODO implement
		return null;
	}

}