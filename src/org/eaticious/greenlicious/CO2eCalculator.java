package org.eaticious.greenlicious;

import java.util.Date;

public interface CO2eCalculator {
	
	public CO2ePrint getProductionValue(Product product, Region producer, Date date);
	
	public CO2ePrint getProductionValue(Product product, Region producer, Date date, Quantity amount);
	
	public CO2ePrint getTransportValue(Product product, Region consumer_region);
	
	public CO2ePrint getTransportValue(Product product, Region consumer_region, Quantity amount);
	
	public CO2ePrint getTransportValue(Product product, Vessel vessel, Integer distance);
	
	public CO2ePrint getTransportValue(Product product, Vessel vessel, Integer distance, Quantity amount);
	
	public CO2ePrint getCoolingValue(Product product, CoolingType type);
	
	public CO2ePrint getShoppingValue(Shoppinglist list, Vessel vessel);
	
	public CO2ePrint getCookingValue(Recipe recipe, Region consumer_region, Date date);
		
	public CO2ePrint getRecipeValue(Recipe recipe, Region user_region, Date date);
	
	public CO2ePrint getRecipeValue(Recipe recipe, Region user_region, Date date, Integer servings);
	
	

}
