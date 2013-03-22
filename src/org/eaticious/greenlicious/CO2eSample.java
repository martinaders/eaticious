package org.eaticious.greenlicious;

import org.eaticious.common.Unit;

public interface CO2eSample{
	
	/**
	 * 
	 * @return The mean CO2e value for the whole process chain
	 */
	Double getMeanValue();
	
	/**
	 * 
	 * @return The standard error for the mean CO2e value
	 */
	Double getStdError();
	
	/**
	 * 
	 * @return The minimal CO2e value for the whole process chain
	 */
	Double getMinimumValue();
	
	/**
	 * 
	 * @return The maximum CO2e value for the whole process chain
	 */
	Double getMaximumValue();
	
	/**
	 * 
	 * @return The unit used for this CO2ePrint
	 */
	Unit getUnit();

	void setMeanValue(Double co2e);

	void setUnit(Unit unit);

}
