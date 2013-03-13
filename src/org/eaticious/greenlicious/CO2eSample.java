package org.eaticious.greenlicious;

import org.eaticious.common.Unit;

public interface CO2eSample{
	
	/**
	 * 
	 * @return The mean CO2e value for the whole process chain
	 */
	public Double getMeanValue();
	
	/**
	 * 
	 * @return The standard error for the mean CO2e value
	 */
	public Double getStdError();
	
	/**
	 * 
	 * @return The minimal CO2e value for the whole process chain
	 */
	public Double getMinimumValue();
	
	/**
	 * 
	 * @return The maximum CO2e value for the whole process chain
	 */
	public Double getMaximumValue();
	
	/**
	 * 
	 * @return The unit used for this CO2ePrint
	 */
	public Unit getUnit();

	public void setMeanValue(Double co2e);

	public void setUnit(Unit u);

}
