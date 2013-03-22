package org.eaticious.common;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.eaticious.eaternity.EmissionFactor;
import org.eaticious.greenlicious.RegionSpecification;

public interface Region extends Serializable {

	/**
	 * Returns the FCL code for this Region or null if the code is unknown
	 * 
	 * @return A String representing the FCL code for this Region or null
	 */
	String getFCLCode();

	/**
	 * DISCUSS Should we return the country code for subregions? DISCUSS Aurelian: you mean a list of the codes? if so,
	 * not necessary because you can get it via getChildren().get(i).getISOCode() ... if there is a iso03 code for a
	 * bigger region than country then yes What whould be nice if the region is not a country, to have something like
	 * the geographical bounderies, but don't know how. DISCUSS No, I meant for smaller regions like a Kanton or
	 * Bundesland... probably null... we might want to add RegionTypes instead, sth. like RegionType.COUNTRY,
	 * RegionType.CONTINENT, RegionType.STATE, RegionType.CITY
	 * 
	 * Returns the ISO3 Code of this Region if it is a country or null otherwise
	 * 
	 * @return ISO3 Code for this Region or this region's parent or null
	 */
	String getISOCode();

	/**
	 * Returns the name of this Region in the specified language or null if there is no translation for the language
	 * 
	 * @param language
	 *            The language in which the name of the Region should be returned
	 * @return A String holding the name of the Region or null
	 */
	String getName(Locale language);

	/**
	 * This returns the parent Region of this Region or null if the parent is not known or the current region is WORLD
	 * 
	 * @return The parent Region of this or null
	 */
	Region getParent();

	/**
	 * This method returns a List<Region> of subregions of this region. All of the subregions belong entirely to this
	 * region. If no subregions are known an empty List<Region> will be returned
	 * 
	 * @return The List<Region> holding subregions of this
	 */
	List<Region> getChildren();

	/**
	 * This will return the region-specific energyMixFactor for conversion from kWh to CO2e
	 * 
	 * @return The EmissionFactor (to be multiplied with kWh in order to calculate CO2e)
	 */
	EmissionFactor getEnergyMixFactor();

	/**
	 * DISCUSS better way to handle this? Aurelian: Hm alright, very detailed but doesn't disturb
	 * 
	 * @return TransportationDetails for this region needed to calculate the correct CO2e values regarding
	 *         transportation of goods
	 */
	RegionSpecification getRegionSpecification();

}
