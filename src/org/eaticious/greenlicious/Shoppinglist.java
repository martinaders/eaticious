package org.eaticious.greenlicious;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface Shoppinglist extends Serializable, Cloneable {
	
	public Map<Product, Quantity> getEntries();
	
	public List<Meal> getMeals();
	
	public void addMeal(Meal meal);
	
	public void removeMeal(Meal meal);
	
	public String getShoppingInfo(Product product);
	
	public boolean getStatus();
	
	public Boolean getStatus(Product product);
	
	public void setStatus (Product product);
	
	public User getUser();
	
	public void setUser(User user);

}
