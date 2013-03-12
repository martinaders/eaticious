package org.eaticious.greenlicious;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface Recipe extends Serializable, Cloneable {
	
	/**
	 * 
	 * @param language
	 * @return The name of this Recipe
	 */
	public String getTitel(Locale language);
	
	/**
	 * 
	 * @param language
	 * @return The cooking instructions for this recipe
	 */
	public String getInstructions(Locale language);
	
	/**
	 * 
	 * @return A List of all Ingredients used by this Recipe
	 */
	public List<Ingredient> getIngredients(Integer servings);
	
	/**
	 * 
	 * @return The cooking time for each appliance type used in this recipe
	 */
	public Map<Appliance, Integer> getApplianceUsage();
	
	/**
	 * 
	 * @return A List holding all Diettypes of this Recipe, e.g. VEGAN, LACTOSE_FREE, etc 
	 */
	public List<Diettype> getDiettypes();
	
	/**
	 * 
	 * @return A Map holding amounts of nutrients per serving of this recipe
	 */
	public Map<Nutrient, Quantity> getNutritionData();
	
	/**
	 * 
	 * @return The URL for the Image of this Recipe or null if not available
	 */
	public URL getImagePath();
	
	/**
	 * 
	 * @return The user rating for this recipe
	 */
	public Integer getRating();
	

}
