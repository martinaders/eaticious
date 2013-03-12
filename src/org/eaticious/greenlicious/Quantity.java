package org.eaticious.greenlicious;

import java.io.Serializable;

public interface Quantity extends Serializable, Cloneable {
	
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
