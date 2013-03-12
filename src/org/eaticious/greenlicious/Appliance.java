package org.eaticious.greenlicious;

import java.io.Serializable;

public interface Appliance extends Serializable, Cloneable {
	
	/**
	 * 
	 * @return The type of the appliance
	 */
	public ApplianceType getType();
	
	/**
	 * 
	 * @return The energy consumption in kW per hour
	 */
	public Double getConsumption();
	
	/**
	 * 
	 * @return The energy source used to run the appliance
	 */
	public EnergySource getSource();

}
