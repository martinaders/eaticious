package org.eaticious.eaternity;

import java.io.Serializable;

public interface EmissionFactor extends Serializable {
	
	/**
	 * 
	 * @return Name of the energy factor / energy mix (e.g. ewz.naturepower)
	 */
	public String getName();
	
	/**
	 * 
	 * @return The emission factor in [kg CO2 / KWh]
	 */
	public Double getFactor();
	

}
