package org.eaticious.greenlicious;

import java.util.Date;
import java.util.Map;

public class CO2eCalculatorImpl implements CO2eCalculator {

	@Override
	public CO2ePrint getProductionValue(Product product, Region producer,
			Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CO2ePrint getProductionValue(Product product, Region producer,
			Date date, Quantity amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CO2ePrint getTransportValue(Product product, Region consumer_region) {
		return this.getTransportValue(product, consumer_region, new QuantityImpl(
				1.0, Unit.KILOGRAM));
	}

	@Override
	public CO2ePrint getTransportValue(Product product, Region consumer_region, Quantity amount) {
		if (!amount.getUnit().isMassUnit()) {
			throw new IllegalArgumentException(
					"Parameter amount has to be an amount of a mass-bbased unit");
		}

		// get sources for product in consumer region
		Map<ProductProducer, Integer> sources = product.getProducers(consumer_region);

		// calculate the co2e emissions
		CO2ePrint result = new CO2ePrintImpl(Unit.KILOGRAM);
		for (ProductProducer producer : sources.keySet()) {
			// get distances by vessels used for shipping
			Map<Vessel, Double> distances = product.getTransportDistances(producer.getRegion(), consumer_region);
			// calculate and add the weighted co2e emissions for each transport
			for (Vessel vessel : distances.keySet()) {
				CO2eSample part = vessel.calculateFreightEmissions(distances.get(vessel), amount);
				// weight by percentage of product from producer in complete market of consumer
				part.setMeanValue(part.getMeanValue() * sources.get(producer));
				result.add(part);
			}
		}
		return result;
	}

	@Override
	public CO2ePrint getTransportValue(Product product, Vessel vessel,
			Integer distance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CO2ePrint getTransportValue(Product product, Vessel vessel,
			Integer distance, Quantity amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CO2ePrint getCoolingValue(Product product, CoolingType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CO2ePrint getShoppingValue(Shoppinglist list, Vessel vessel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CO2ePrint getCookingValue(Recipe recipe, Region consumer_region,
			Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CO2ePrint getRecipeValue(Recipe recipe, Region user_region, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CO2ePrint getRecipeValue(Recipe recipe, Region user_region,
			Date date, Integer servings) {
		// TODO Auto-generated method stub
		return null;
	}

}
