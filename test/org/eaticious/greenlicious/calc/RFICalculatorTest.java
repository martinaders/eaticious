package org.eaticious.greenlicious.calc;

import static org.junit.Assert.*;

import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Unit;
import org.junit.Before;
import org.junit.Test;

public class RFICalculatorTest {
	
	private Quantity distance;

	@Before
	public void setUp() throws Exception {
		this.distance = new QuantityImpl(1d, Unit.KILOMETER);
	}

	@Test
	public void testGetRFIFactorStandard() {
		// lower end values, no share of flight near stratosphere, ECOTransIT values
		distance.setAmount(499.0);
		assertTrue(1d == RFICalculator.getRFIFactor(distance));
		distance.setAmount(500d);
		assertTrue(1d == RFICalculator.getRFIFactor(distance));
		distance.setAmount(501d);
		assertFalse(1d == RFICalculator.getRFIFactor(distance));
		
		// increasing share of flight near stratosphere, ECOTransIT values
		distance.setAmount(749d);
		assertFalse(1.81 == RFICalculator.getRFIFactor(distance));
		distance.setAmount(750d);
		assertTrue(1.81 == RFICalculator.getRFIFactor(distance));
		distance.setAmount(751d);
		assertFalse(1.81 == RFICalculator.getRFIFactor(distance));
		distance.setAmount(1000d);
		assertTrue(2.18 == RFICalculator.getRFIFactor(distance));
		distance.setAmount(2000d);
		assertTrue(2.52 == RFICalculator.getRFIFactor(distance));
		distance.setAmount(4000d);
		assertTrue(2.73 == RFICalculator.getRFIFactor(distance));
		
		// upper end values, all the flight near stratosphere, ECOTransIT values
		distance.setAmount(9999d);
		assertFalse(2.87 == RFICalculator.getRFIFactor(distance));
		distance.setAmount(10000d);
		assertTrue(2.87 == RFICalculator.getRFIFactor(distance));
		distance.setAmount(200000d);
		assertTrue(2.87 == RFICalculator.getRFIFactor(distance));
	}
	
	@Test
	public void testGetRFIFactorInterpolated() {
		distance.setAmount(563d);
		assertTrue(1.20412 == RFICalculator.getRFIFactor(distance));
		distance.setAmount(2783d);
		assertTrue(2.602215 == RFICalculator.getRFIFactor(distance));
		distance.setAmount(10500d);
		assertTrue(2.87 == RFICalculator.getRFIFactor(distance));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetRFIFactorExceptionZero() {
		distance.setAmount(0d);
		RFICalculator.getRFIFactor(distance);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetRFIFactorExceptionNegative() {
		distance.setAmount(-7d);
		RFICalculator.getRFIFactor(distance);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWrongUnit() {
		distance.setAmount(10d);
		distance.setUnit(Unit.CO2);
		RFICalculator.getRFIFactor(distance);
	}

}
