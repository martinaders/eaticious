package org.eaticious.common;

import java.io.Serializable;
import java.util.Locale;

public interface Nutrient extends Serializable {
	
	String getName();
	
	String getName(Locale language);
	
	Quantity getRecommendedDailyConsumption();

}
