package org.eaticious.greenlicious.calc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RFICalculatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRFIFactor_standard() {
		// lower end values, no share of flight near stratosphere, ECOTransIT values
		assertTrue(1d == RFICalculator.getRFIFactor(499d));
		assertTrue(1d == RFICalculator.getRFIFactor(500d));
		assertFalse(1d == RFICalculator.getRFIFactor(501d));
		
		// increasing share of flight near stratosphere, ECOTransIT values
		assertFalse(1.81 == RFICalculator.getRFIFactor(749d));
		assertTrue(1.81 == RFICalculator.getRFIFactor(750d));
		assertFalse(1.81 == RFICalculator.getRFIFactor(751d));
		assertTrue(2.18 == RFICalculator.getRFIFactor(1000d));
		assertTrue(2.52 == RFICalculator.getRFIFactor(2000d));
		assertTrue(2.73 == RFICalculator.getRFIFactor(4000d));
		
		// upper end values, all the flight near stratosphere, ECOTransIT values
		assertFalse(2.87 == RFICalculator.getRFIFactor(9999d));
		assertTrue(2.87 == RFICalculator.getRFIFactor(10000d));
		assertTrue(2.87 == RFICalculator.getRFIFactor(200000d));
	}
	
	@Test
	public void testGetRFIFactor_interpolated() {
		assertTrue(1.20412 == RFICalculator.getRFIFactor(563d));
		assertTrue(2.602215 == RFICalculator.getRFIFactor(2783d));
		assertTrue(2.87 == RFICalculator.getRFIFactor(10500d));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetRFIFactorException_zero() {
		RFICalculator.getRFIFactor(0d);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetRFIFactorException_negative() {
		RFICalculator.getRFIFactor(-7d);
	}

}
