package org.eaticious.common;

import java.io.Serializable;
import java.util.Locale;

public interface Nutrient extends Serializable {
	
	public String getName();
	
	public String getName(Locale language);
	
	public Quantity getRecommendedDailyConsumption();

}
