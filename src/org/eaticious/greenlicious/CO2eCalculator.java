package org.eaticious.greenlicious;

import java.util.Date;

import org.eaticious.common.FoodProduct;
import org.eaticious.common.Quantity;
import org.eaticious.common.Recipe;
import org.eaticious.common.Region;

public interface CO2eCalculator {
	
	public CO2ePrint getFoodProductionValue(FoodProduct FoodProduct, Region producer, Date date);
	
	public CO2ePrint getFoodProductionValue(FoodProduct FoodProduct, Region producer, Date date, Quantity amount);
	
	public CO2ePrint getTransportValue(FoodProduct FoodProduct, Region consumer_region);
	
	public CO2ePrint getTransportValue(FoodProduct FoodProduct, Region consumer_region, Quantity amount);
	
	public CO2ePrint getTransportValue(FoodProduct FoodProduct, Vessel vessel, Integer distance);
	
	public CO2ePrint getTransportValue(FoodProduct FoodProduct, Vessel vessel, Integer distance, Quantity amount);
	
	public CO2ePrint getCoolingValue(FoodProduct FoodProduct, CoolingType type);
	
	public CO2ePrint getShoppingValue(Shoppinglist list, Vessel vessel);
	
	public CO2ePrint getCookingValue(Recipe recipe, Region consumer_region, Date date);
		
	public CO2ePrint getRecipeValue(Recipe recipe, Region user_region, Date date);
	
	public CO2ePrint getRecipeValue(Recipe recipe, Region user_region, Date date, Integer servings);
	
	

}
