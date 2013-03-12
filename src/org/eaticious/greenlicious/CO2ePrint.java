package org.eaticious.greenlicious;

import java.io.Serializable;
import java.util.List;

public interface CO2ePrint extends Serializable, Cloneable{
	
	/**
	 * Adds a CO2ePrint to this or throws an exception if the Unit of the CO2ePrint used cannot be converted into the Unit of this
	 * @param part a print to be added to this
	 * @return true if parameter was added, false otherwise (iff for some reason exception is not thrown)
	 * 
	 */
	public boolean add(CO2eSample sample) throws IllegalArgumentException;
	
	public List<CO2eSample> getSamples();
	
	public void remove(CO2eSample sample);
	
	public Unit getUnit();

	Double getMeanValue();

	Double getStdError();

	Double getMinimumValue();

	Double getMaximumValue();

}
