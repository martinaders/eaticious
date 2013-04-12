package org.eaticious.common;

import java.io.Serializable;

public class QuantityImpl implements Quantity {
	
	private static final long serialVersionUID = -2234968030318875678L;
	// APPROVE changed to final because unit should not be changed anymore
	private final Unit unit;
	private Double amount;
	
	/**
	 * Empty Constructor needed for objectify
	 */
	private QuantityImpl() { /* needed for objectify */ 
		unit = Unit.NONE;
	}
	
	// APPROVE convenience method for setting the unit of a quantity without giving it an amount
	public QuantityImpl(final Unit unit) {
		this.unit = unit;
	}
	
	public QuantityImpl(final Double amount, final Unit unit){
		this.unit = unit;
		this.amount = amount;
	}
	
	/**
	 * Copy constructor
	 */
	public QuantityImpl(final Quantity other) {
		this(new Double(other.getAmount()), other.getUnit());
	}

	@Override
	public Double getAmount() {
		return this.amount;
	}

	@Override
	public Unit getUnit() {
		return this.unit;
	}

	@Override
	public Quantity convert(final Unit unit) {
		return new QuantityImpl(this.unit.convert(this.getAmount(), unit), unit);
	}

	@Override
	public void setAmount(final double amount) {
		this.amount = amount;
	}
	
	@Override
	public boolean equals(Object comparator){
		boolean result;
		if(comparator instanceof Quantity && this.getUnit().getDimension() == ((Quantity)comparator).getUnit().getDimension()){
			Double amountToCompare = ((Quantity)comparator).convert(this.getUnit()).getAmount();
			result = this.getAmount().equals(amountToCompare);
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public void add(Quantity add) {
		this.setAmount(this.getAmount() + add.convert(this.getUnit()).getAmount());
		
	}

	@Override
	public void substract(Quantity sub) {
		this.setAmount(this.getAmount() - sub.convert(this.getUnit()).getAmount());
		
	}

}
