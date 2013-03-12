package org.eaticious.greenlicious;

import java.util.Locale;

public interface Nutrient {
	
	public String getName();
	
	public String getName(Locale language);
	
	public Quantity getRecommendedDailyConsumption();

}
