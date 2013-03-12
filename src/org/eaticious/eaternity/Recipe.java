package org.eaticious.eaternity;

import java.util.List;
import java.util.Locale;
import org.eaticious.common.*;
import org.eaticious.greenlicious.Appliance;
import org.eaticious.greenlicious.Ingredient;

import java.util.Map;

public interface Recipe {

	/**
	 * 
	 * @param language
	 * @return The name of this Recipe
	 */
	public String getTitel(Locale language);
	
	/**
	 * 
	 * @param language
	 * @return The accompanying phrase of this Recipe
	 */
	public String getSubTitel(Locale language);

	
	public Integer getStdServingSize();
	
	/**
	 * 
	 * @return A List of all Ingredients used by this Recipe
	 */
	public List<Ingredient> getIngredients(Integer servings);
	
	/**
	 * 
	 * @return The cooking time for each appliance type used in this recipe
	 */
	public Map<Device, Integer> getDeviceUsage();
	
	/**
	 * 
	 * @param language
	 * @return The cooking instructions for this recipe
	 */
	public String getInstructions(Locale language);
	
	/**
	 * 
	 * @return A Map holding amounts of nutrients per serving of this recipe
	 */
	public Map<Nutrient, Quantity> getNutritionData();
	
	/**
	 * 
	 * @return The user rating for this recipe
	 */
	public Integer getRating();
	
	
	// eaternity specific
	public List<RecipeComment> getComments();
	
	public Long getAncestorId();
	
	public boolean isOpen();
	
	public boolean isDeleted();
	
	public Long getUserID();

}
