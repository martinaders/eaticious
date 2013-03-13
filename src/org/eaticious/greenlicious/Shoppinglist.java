package org.eaticious.greenlicious;

import java.util.List;
import java.util.Map;

import org.eaticious.common.Dish;
import org.eaticious.common.FoodProduct;
import org.eaticious.common.Quantity;
import org.eaticious.common.User;

public interface Shoppinglist {
	
	public Map<FoodProduct, Quantity> getEntries();
	
	public List<Dish> getMeals();
	
	public void addMeal(Dish meal);
	
	public void removeMeal(Dish meal);
	
	public String getShoppingInfo(FoodProduct product);
	
	public boolean getStatus();
	
	public Boolean getStatus(FoodProduct product);
	
	public void setStatus (FoodProduct product);
	
	public User getUser();
	
	public void setUser(User user);

}
