package org.eaticious.greenlicious;

import java.io.Serializable;
import java.util.Date;

public interface Meal extends Serializable, Cloneable {
	
	public Recipe getRecipe();
	
	public Integer getScheduledServings();
	
	public Date getScheduledDay();

}
