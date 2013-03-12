package org.eaticious.eaternity;

import org.eaticious.greenlicious.EnergySource;

public interface Device {

	public String getName();
	
	/**
	 * 
	 * @return The subtype of the device, e.g. small or big Microwave
	 */
	public String getSpecification();
	
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
