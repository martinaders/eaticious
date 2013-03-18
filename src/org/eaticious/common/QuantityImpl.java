package org.eaticious.common;

public class QuantityImpl implements Quantity {
	
	private static final long serialVersionUID = -2234968030318875678L;
	
	private Unit unit;
	private Double amount;
	
	/**
	 * Empty Constructor needed for objectify
	 */
	public QuantityImpl() {}
	
	public QuantityImpl(Double amount, Unit unit){
		this.unit = unit;
		this.amount = amount;
	}
	
	/**
	 * Copy constructor
	 */
	public QuantityImpl(Quantity other) {
		unit = other.getUnit();
		amount = new Double(amount);
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
	public Quantity convert(Unit unit) {
		Quantity result = new QuantityImpl(this.unit.convert(this.getAmount(), unit), unit);
		return result;
	}

	@Override
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

}
