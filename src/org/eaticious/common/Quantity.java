package org.eaticious.common;

import java.io.Serializable;

public interface Quantity extends Serializable {
	
	/**
	 * 
	 * @return the numeric amount 
	 */
	Double getAmount();
	
	/**
	 * 
	 * @param amount the numeric amount
	 */
	void setAmount(double amount);
	
	/**
	 * 
	 * @return the unit used
	 */
	Unit getUnit();

	/**
	 * 
	 * @param unit
	 * @return
	 */
	Quantity convert(Unit unit) throws IllegalArgumentException;

	void add(Quantity co2eForRoute);

	void substract(Quantity co2eForRoute);

}
