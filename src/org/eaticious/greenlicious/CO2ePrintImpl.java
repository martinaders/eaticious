package org.eaticious.greenlicious;

import java.util.ArrayList;
import java.util.List;

import org.eaticious.common.Unit;

public class CO2ePrintImpl extends ArrayList<CO2eSample> implements CO2ePrint {
	
	private Unit unit;
	
	public CO2ePrintImpl(final Unit unit){
		super();
		this.unit = unit;
	}

	@Override
	public Double getMeanValue() {
		Double result = 0.0;
		for(final CO2eSample sample : this){
			result += sample.getMeanValue();
		}
		return result;
	}

	@Override
	public Double getStdError() {
		Double result = 0.0;
		for(final CO2eSample sample : this){
			result += sample.getStdError();
		}
		return result;
	}

	@Override
	public Double getMinimumValue() {
		Double result = 0.0;
		for(final CO2eSample sample : this){
			result += sample.getMinimumValue();
		}
		return result;
	}

	@Override
	public Double getMaximumValue() {
		Double result = 0.0;
		for(final CO2eSample sample : this){
			result += sample.getMaximumValue();
		}
		return result;
	}

	@Override
	public Unit getUnit() {
		return this.unit;
	}

	@Override
	public boolean add(final CO2eSample sample){
		if(!sample.getUnit().equals(this.unit)){
			throw new IllegalArgumentException("Sample has a different unit than this print (needs " + this.getUnit() +")");
		}
		return super.add(sample);
	}

	@Override
	public List<CO2eSample> getSamples() {
		return this;
	}

	@Override
	public void remove(final CO2eSample sample) {
		super.remove(sample);
	}

}
