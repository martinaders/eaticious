package org.eaticious.greenlicious.vessels;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;

import org.eaticious.greenlicious.vessels.AirplaneSpecification.AirplaneSize;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AirplaneSpecificationTest {
	
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
	public void testGetSize() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, Collections.<Integer, Double> emptyMap() );
		assertSame(AirplaneSize.SMALL, spec.getSize());
	}

	@Test
	public void testGetMaxRange() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, Collections.<Integer, Double> emptyMap() );
		assertTrue(100 == spec.getMaxRange());
	}

	@Test
	public void testGetMaxPayload() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, Collections.<Integer, Double> emptyMap() );
		assertTrue(2 == spec.getMaxPayload());
	}

	@Test
	public void testGetSeats() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, Collections.<Integer, Double> emptyMap() );
		assertTrue(1 == spec.getSeats());
	}

	@Test
	public void testHasConsumptionData() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Integer, Double>() );
		assertFalse(spec.hasConsumptionData());
		
		spec.addConsumptionEntry(10, 100d);
		assertTrue(spec.hasConsumptionData());
	}

	@Test
	public void testGetConsumptionProfile() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Integer, Double>() );
		spec.addConsumptionEntry(10, 100d);
		spec.addConsumptionEntry(10, 150d);
		spec.addConsumptionEntry(20, 200d);
		assertTrue(2 == spec.getConsumptionProfile().size());
		assertTrue(150 == spec.getConsumptionProfile().get(10));
		assertTrue(200 == spec.getConsumptionProfile().get(20));
		
	}

	@Test
	public void testAddConsumptionEntry() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Integer, Double>() );
		spec.addConsumptionEntry(10, 100d);
		assertTrue(spec.hasConsumptionData());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddConsumptionEntryNullKey() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Integer, Double>() );
		spec.addConsumptionEntry(null, 100d);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddConsumptionEntryNullValue() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Integer, Double>() );
		spec.addConsumptionEntry(10, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddConsumptionEntryNegKey() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Integer, Double>() );
		spec.addConsumptionEntry(-10, 100d);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddConsumptionEntryNegValue() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Integer, Double>() );
		spec.addConsumptionEntry(10, -100d);
	}

}
