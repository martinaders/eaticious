package org.eaticious.common;

import java.io.Serializable;

public interface Quantity extends Serializable {
	
	/**
	 * 
	 * @return the numeric amount 
	 */
	public Double getAmount();
	
	/**
	 * 
	 * @param amount the numeric amount
	 */
	public void setAmount(double amount);
	
	/**
	 * 
	 * @return the unit used
	 */
	public Unit getUnit();
	
	/**
	 * 
	 * @param unit the numeric amount
	 */
	public void setUnit(Unit unit);

	/**
	 * 
	 * @param unit
	 * @return
	 */
	public Quantity convert(Unit unit);

}
