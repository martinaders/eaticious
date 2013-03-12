package org.eaticious.greenlicious;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Serialize;

public class CO2ePrintImpl extends ArrayList<CO2eSample> implements CO2ePrint {
	
	@Serialize
	private Unit unit;
	
	public CO2ePrintImpl(Unit unit){
		this.unit = unit;
	}

	@Override
	public Double getMeanValue() {
		Double result = 0.0;
		for(CO2eSample sample : this){
			result += sample.getMeanValue();
		}
		return result;
	}

	@Override
	public Double getStdError() {
		Double result = 0.0;
		for(CO2eSample sample : this){
			result += sample.getStdError();
		}
		return result;
	}

	@Override
	public Double getMinimumValue() {
		Double result = 0.0;
		for(CO2eSample sample : this){
			result += sample.getMinimumValue();
		}
		return result;
	}

	@Override
	public Double getMaximumValue() {
		Double result = 0.0;
		for(CO2eSample sample : this){
			result += sample.getMaximumValue();
		}
		return result;
	}

	@Override
	public Unit getUnit() {
		return this.unit;
	}

	@Override
	public boolean add(CO2eSample object){
		if(!object.getUnit().equals(this.unit)){
			throw new IllegalArgumentException("Sample has a different unit than this print (needs " + this.getUnit() +")");
		}
		return super.add(object);
	}

	@Override
	public List<CO2eSample> getSamples() {
		return this;
	}

	@Override
	public void remove(CO2eSample sample) {
		super.remove(sample);
	}

}
