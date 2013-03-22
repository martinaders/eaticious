package org.eaticious.greenlicious;

import org.eaticious.common.Unit;


public class CO2eSampleImpl implements CO2eSample {
	
	private Double meanValue;
	
	private Double stdError;
	
	private Double minimumValue;
	
	private Double maximumValue;
	
	private Unit unit;

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
		return this.minimumValue;
	}

	@Override
	public Double getMaximumValue() {
		return this.maximumValue;
	}

	@Override
	public Unit getUnit() {
		return this.unit;
	}

	@Override
	public void setMeanValue(final Double co2e) {
		this.maximumValue = co2e;
	}
	
	public void setStdError(final Double error) {
		this.stdError = error;
	}

	public void setMinimumValue(final Double value) {
		this.minimumValue = value;
	}

	public void setMaximumValue(final Double value) {
		this.maximumValue = value;
	}

	@Override
	public void setUnit(final Unit unit) {
		this.unit = unit;
	}
}
