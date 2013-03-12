package org.eaticious.greenlicious;

import java.io.Serializable;
import java.util.Map;

public interface Ingredient extends Serializable, Cloneable {

	/**
	 * 
	 * @return The recipe using this ingredient
	 */
	public Recipe getRecipe();
	
	/**
	 * 
	 * @return The Product of this Ingredient
	 */
	public Product getProduct();
	
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