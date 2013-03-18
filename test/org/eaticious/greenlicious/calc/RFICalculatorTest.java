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
		assertTrue(1d == RFICalculator.getRFIFactor(499));
		assertTrue(1d == RFICalculator.getRFIFactor(500));
		assertFalse(1d == RFICalculator.getRFIFactor(501));
		
		// increasing share of flight near stratosphere, ECOTransIT values
		assertFalse(1.81 == RFICalculator.getRFIFactor(749));
		assertTrue(1.81 == RFICalculator.getRFIFactor(750));
		assertFalse(1.81 == RFICalculator.getRFIFactor(751));
		assertTrue(2.18 == RFICalculator.getRFIFactor(1000));
		assertTrue(2.52 == RFICalculator.getRFIFactor(2000));
		assertTrue(2.73 == RFICalculator.getRFIFactor(4000));
		
		// upper end values, all the flight near stratosphere, ECOTransIT values
		assertFalse(2.87 == RFICalculator.getRFIFactor(9999));
		assertTrue(2.87 == RFICalculator.getRFIFactor(10000));
		assertTrue(2.87 == RFICalculator.getRFIFactor(200000));
	}
	
	@Test
	public void testGetRFIFactor_interpolated() {
		assertTrue(1.20412 == RFICalculator.getRFIFactor(563));
		assertTrue(2.602215 == RFICalculator.getRFIFactor(2783));
		assertTrue(2.87 == RFICalculator.getRFIFactor(10500));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetRFIFactorException_zero() {
		RFICalculator.getRFIFactor(0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetRFIFactorException_negative() {
		RFICalculator.getRFIFactor(-7);
	}

}
