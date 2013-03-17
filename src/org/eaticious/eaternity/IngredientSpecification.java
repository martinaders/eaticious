package org.eaticious.eaternity;

import java.util.Map;

import org.eaticious.common.*;

import vessels.Vessel;


public interface IngredientSpecification {
	
	public FoodProduct getFoodProduct();
	
	/**
	 * 
	 * @return The Amount of this Ingredient needed in the associated Recipe, the original unit is preserved
	 */
	public Quantity getAmount();
	
	/**
	 * 
	 * @return The normalized Amount of this Ingredient needed in the associated Recipe, the Amount is standardized in grams / milliliters
	 */
	public Quantity getNormalizedAmount();
	
	/**
	 * 
	 * @return A Map holding amounts of nutrients per serving of the associated recipe
	 */
	public Map<Nutrient, Quantity> getNutritionData();

	
	// eaternity specific
	
	/**
	 * Eaternity probably doesn't need the detailed vessel enum
	 * 
	 * @param origin The producing location
	 * @param target The consuming location
	 * @return The distances by vessel used for transport
	 */
	public Map<Vessel, Double> getTransportDistances(String origin, String target);
	
	public CO2Value getCO2Value();
	
	public Season getSeason();
	
	public Region getRegion();
	
	public Production getProduction();
	
	public Conservation getConservation();
	
	public Double getCost();
	
}
