package org.eaticious.greenlicious;

public class QuantityImpl implements Quantity {
	
	private Unit unit;
	private Double amount;
	
	public QuantityImpl(Double amount, Unit unit){
		this.unit = unit;
		this.amount = amount;
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

}
