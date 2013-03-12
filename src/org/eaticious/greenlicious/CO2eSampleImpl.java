package org.eaticious.greenlicious;


public class CO2eSampleImpl implements CO2eSample {
	
	private Double meanValue;
	
	private Double stdError;
	
	private Double minValue;
	
	private Double maxValue;
	
	private Unit unit;
	
	public CO2eSampleImpl(Unit unit){
		this.unit = Unit.KILOGRAM;
	}

	@Override
	public Double getMeanValue() {
		return this.meanValue;
	}

	@Override
	public Double getStdError() {
		return this.stdError;
	}

	@Override
	public Double getMinimumValue() {
		return this.minValue;
	}

	@Override
	public Double getMaximumValue() {
		return this.maxValue;
	}

	@Override
	public Unit getUnit() {
		return this.unit;
	}

	@Override
	public void setMeanValue(Double co2e) {
		this.maxValue = co2e;
	}

	@Override
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}
