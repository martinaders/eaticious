package org.eaticious.eaternity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.eaticious.greenlicious.Recipe;

public interface Dish extends Serializable {
	
	public Recipe getRecipe();
	
	public Integer getServings();
	
	public String getLocation();
	
	public Date getCookingDate();
	
	
	
	// eaternity specific
	
	public List<IngredientSpecification> getIngredients(Integer servings);
	
	public Long getUserID();
	
	public Long getKitchenId();
	
	// make Recipe a member field for not persisting it as well seperatly
	
	public EmissionFactor getEmissionFactor();
	
	/**
	 * 
	 * @return false if the Ingredients are not specified and thus weighted averages are taken
	 */
	public boolean isSpecified();
	
	/**
	 * depends on isSpecified which values is returned
	 * @return
	 */
	public Double getCO2Value();
}
