package org.eaticious.common;

public class QuantityImpl implements Quantity {
	
	private static final long serialVersionUID = -2234968030318875678L;
	
	private Unit unit;
	private Double amount;
	
	/**
	 * Empty Constructor needed for objectify
	 */
	public QuantityImpl() { /* needed for objectify */ }
	
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
	public void setUnit(final Unit unit) {
		this.unit = unit;
	}

}
