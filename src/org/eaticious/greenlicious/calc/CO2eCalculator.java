package org.eaticious.greenlicious.calc;

import java.util.Date;

import org.eaticious.common.FoodProduct;
import org.eaticious.common.Quantity;
import org.eaticious.common.Recipe;
import org.eaticious.common.Region;
import org.eaticious.greenlicious.CO2ePrint;
import org.eaticious.greenlicious.CoolingType;
import org.eaticious.greenlicious.Shoppinglist;
import org.eaticious.greenlicious.vessels.Vessel;


public interface CO2eCalculator {
	
	CO2ePrint getFoodProductionValue(FoodProduct product, Region producer, Date date);
	
	CO2ePrint getFoodProductionValue(FoodProduct product, Region producer, Date date, Quantity amount);
	
	CO2ePrint getTransportValue(FoodProduct product, Region consumerRegion);
	
	CO2ePrint getTransportValue(FoodProduct product, Region consumerRegion, Quantity amount);
	
	CO2ePrint getTransportValue(FoodProduct product, Vessel vessel, Integer distance);
	
	CO2ePrint getTransportValue(FoodProduct product, Vessel vessel, Integer distance, Quantity amount);
	
	CO2ePrint getCoolingValue(FoodProduct product, CoolingType type);
	
	CO2ePrint getShoppingValue(Shoppinglist list, Vessel vessel);
	
	CO2ePrint getCookingValue(Recipe recipe, Region consumerRegion, Date date);
		
	CO2ePrint getRecipeValue(Recipe recipe, Region userRegion, Date date);
	
	CO2ePrint getRecipeValue(Recipe recipe, Region userRegion, Date date, Integer servings);
	
	

}
