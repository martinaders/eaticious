package org.eaticious.greenlicious;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface Product extends Serializable, Cloneable {

	/**
	 * 
	 * @param region The region for which the season is requested
	 * @return The actual season in the requested region 
	 */
	public Season getCurrentSeason(Region region);
	
	/**
	 * 
	 * @return The code of this product in the requested product classification or null if unknown
	 */
	public String getCode(ProductClassification classification);
	
	/**
	 * 
	 * @return A List holding all known consumers for this product
	 */
	public List<ProductConsumer> getConsumers();
	
	/**
	 * 
	 * @param producer The producing country
	 * @return A List holding all known consumers of this product produced by the producer
	 */
	public List<ProductConsumer> getConsumers(Region producer);
	
	/**
	 * 
	 * @param lang The language for the requested name
	 * @return The name of the product in the requested language or nulll if the name is not known in that language
	 */
	public String getName(Locale lang);
	
	/**
	 * 
	 * @return A Map holding amounts of nutrients per 100 grams of this product
	 */
	public Map<Nutrient, Quantity> getNutritionData();
	
	/**
	 * 
	 * @return A List holding all producers known for this product 
	 */
	public List<ProductProducer> getProducers();
	
	/**
	 * 
	 * @param consumer The consuming region
	 * @return A Map holding all known producers for this product in the consuming region and the corresponding percentage the producer adds to the consumer's market
	 */
	public Map<ProductProducer, Integer> getProducers(Region consumer);
	
	/**
	 * 
	 * @return A List of known recipes for this product
	 */
	public List<Recipe> getRecipes();
	
	/**
	 * The scientific name of the product or null if the scientific name is not known
	 * @return
	 */
	public String getScientificName();
	
	
	/**
	 * 
	 * @param region The region for which the season is requested
	 * @param date The date for which the season should be determined
	 * @return The season in the requested region at the requested date or null if unknown
	 */
	public Season getSeason(Region region, Date date);
	
	/**
	 * 
	 * @param region The region for which the seasons are requested
	 * @return A List with all known production seasons for the ingredient in the requested country 
	 */
	public List<Season> getSeasons(Region region);
	
	/**
	 * 
	 * @param producer The producing area
	 * @param consumer The consuming area
	 * @return The distances by vessel used for transport
	 */
	public Map<Vessel, Double> getTransportDistances(Region producer, Region consumer);
	
	/**
	 * @return A String giving additional information about what to bear in mind when buying this product
	 */
	public String getShoppingInfo();
	
	/**
	 * 
	 * @return A List holding all products which are known substitutes for this product
	 */
	public List<Product> getSubstitutes();
	
	/**
	 * 
	 * @return A List holding all Diettypes of this Recipe, e.g. VEGAN, LACTOSE_FREE, etc 
	 */
	public List<Diettype> getDiettypes();
}
