package org.eaticious.common;

import java.io.Serializable;
import java.util.Date;

public interface Dish extends Serializable {
	
	public Recipe getRecipe();
	
	public Integer getServings();
	
	public Date getCookingDate();
	
	public Quantity getCO2eValue();
	
	public User getUser();
	
	public Long getKitchenId();
}
