package org.eaticious.common;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.eaticious.greenlicious.TransportationDetails;

public interface Region extends Serializable {
	
	/**
	 * APPROVE Aurelian: is this ok for you?
	 * 
	 * used to describe the landscape of a region, used mainly when calculating co2e from commercial transport
	 *
	 */
	public enum Landscape {
		FLAT, HILL, MOUNTAIN;
	}

	/**
	 * Returns the FCL code for this Region or null if the code is unknown
	 * @return A String representing the FCL code for this Region or null
	 */
	public String getFCLCode();

	/**
	 * DISCUSS Should we return the country code for subregions?
	 * Returns the ISO3 Code of this Region if it is a country or null otherwise
	 * @return ISO3 Code for this Region or this region's parent or null
	 */
	public String getISOCode();

	/**
	 * CHECK do we need this? What will be returned?
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Returns the name of this Region in the specified language or null if
	 * there is no translation for the language
	 * 
	 * @param language
	 *            The language in which the name of the Region should be
	 *            returned
	 * @return A String holding the name of the Region or null
	 */
	public String getName(Locale language);

	/**
	 * This returns the parent Region of this Region or null if the parent is
	 * not known or the current region is WORLD
	 * 
	 * @return The parent Region of this or null
	 */
	public Region getParent();

	/**
	 * This method returns a List<Region> of subregions of this region. All of
	 * the subregions belong entirely to this region. If no subregions are known
	 * an empty List<Region> will be returned
	 * 
	 * @return The List<Region> holding subregions of this
	 */
	public List<Region> getChildren();

	/**
	 * APPROVE Aurealian, fine with you?
	 * 
	 * This will return the region-specific energyMixFactor for conversion from
	 * kWh to CO2e
	 * 
	 * @return The factor to be multiplied with kWh in order to calculate CO2e
	 */
	public double getEnergyMixFactor();

	/**
	 * DISCUSS better way to handle this?
	 * 
	 * @return TransportationDetails for this region needed to calculate the
	 *         correct CO2e values regarding transportation of goods
	 */
	public TransportationDetails getTransportationDetails();

}
