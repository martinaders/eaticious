package org.eaticious.common;

import java.io.Serializable;
import java.util.Map;

import org.eaticious.greenlicious.Nutrient;
import org.eaticious.greenlicious.Quantity;

public interface Ingredient extends Serializable {
	
	/**
	 * 
	 * @return The food product from which this ingredient is derived
	 */
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

	
}
