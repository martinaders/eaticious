package org.eaticious.eaternity;

import java.util.Locale;

import org.eaticious.greenlicious.EnergySource;

public interface Device {

	/**
	 * 
	 * @param language
	 * @return name of the device in the locale language
	 */
	public String getName(Locale locale);
	
	/**
	 * 
	 * @return The subtype of the device, e.g. small or big Microwave
	 */
	public String getSpecification(Locale locale);
	
	/**
	 * 
	 * @return The energy consumption in kW per hour
	 */
	public Double getConsumption();
	
	/**
	 * 
	 * @return The energy source used to run the appliance
	 */
	public EnergySource getEnergySource();
}
