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
	 * @return the unit used
	 */
	public Unit getUnit();

	/**
	 * 
	 * @param unit
	 * @return
	 */
	public Quantity convert(Unit unit);

}
