package org.eaticious.greenlicious.calc;

import java.util.Date;

import org.eaticious.common.FoodProduct;
import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Recipe;
import org.eaticious.common.Region;
import org.eaticious.common.Unit;
import org.eaticious.greenlicious.CoolingType;
import org.eaticious.greenlicious.Shoppinglist;
import org.eaticious.greenlicious.vessels.Vessel;


public class CO2eCalculatorImpl implements CO2eCalculator {


	@Override
	public Quantity getTransportValue(final FoodProduct product, final Region consumerRegion) {
		return this.getTransportValue(product, consumerRegion, new QuantityImpl(
				1.0, Unit.KILOGRAM));
	}

	@Override
	public Quantity getTransportValue(FoodProduct product, Region consumerRegion, Quantity amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity getTransportValue(FoodProduct product, Vessel vessel,
			Integer distance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity getTransportValue(FoodProduct product, Vessel vessel,
			Integer distance, Quantity amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity getCoolingValue(FoodProduct product, CoolingType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity getShoppingValue(Shoppinglist list, Vessel vessel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity getCookingValue(Recipe recipe, Region consumerRegion,
			Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity getRecipeValue(Recipe recipe, Region userRegion, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity getRecipeValue(Recipe recipe, Region userRegion,
			Date date, Integer servings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity getFoodProductionValue(FoodProduct product,
			Region producer, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quantity getFoodProductionValue(FoodProduct product,
			Region producer, Date date, Quantity amount) {
		// TODO Auto-generated method stub
		return null;
	}

}
