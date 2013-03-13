package org.eaticious.eaternity;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.eaticious.common.*;


public interface Ingredient {

	public String getName(Locale locale);
	
	/**
	 * @return The code of this product in the requested product classification or null if unknown
	 */
	public String getCode(ProductClassification classification);
	
	/**
	 * 
	 * @return A Map holding amounts of nutrients per serving of the associated recipe
	 */
	public Map<Nutrient, Quantity> getNutritionData();
	
	
	public List<Ingredient> getAlternatives();
	
	/**
	 * probably implementable with Growth Degree Days
	 * @return
	 */
	public Map<Region,Season> getSeasons();
	
	/**
	 * 
	 * @return density of ingredient for correct volume to weight transformation (EL, TL to grams)
	 */
	public Double getDensity();
	
	public List<String> getTags();
	
	public Quantity getStdWeight();
	
	/**
	 * 
	 * @param consumer The consuming region
	 * @return A Map holding all known producers for this product in the consuming region and the corresponding percentage the producer adds to the consumer's market
	 */
	public Map<Region, Integer> getProducingRegions(Region consumer);
	
	
	
	// eaternity specific
	
	//probably (if not possible via ProductClassification)
	public String getCategory();
	
	public CO2Value getCO2Value();
	
	// not necessary with faostat data
	public Region getStdRegion();
	
	
	public List<Production> getProductions();
	
	public List<Transportation> getTransportations();
	
	public List<Conservation> getConservations();
	
	
}
