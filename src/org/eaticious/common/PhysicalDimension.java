package org.eaticious.common;

public enum PhysicalDimension {

	MASS(Unit.KILOGRAM), VOLUME(Unit.LITRE), DISTANCE(Unit.KILOMETER), ENERGY(Unit.KILOWATTHOUR), OTHER(Unit.NONE);
	
	private Unit baseUnit;
	
	private PhysicalDimension(Unit baseunit){
		this.baseUnit = baseunit;
	}
	
	public Unit getBaseUnit(){
		return this.baseUnit;
	}
}
