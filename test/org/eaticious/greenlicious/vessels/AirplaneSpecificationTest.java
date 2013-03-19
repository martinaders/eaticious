package org.eaticious.greenlicious.vessels;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;

import org.eaticious.greenlicious.vessels.AirplaneSpecification.AirplaneSize;
import org.eaticious.greenlicious.vessels.AirplaneSpecification.StandardModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AirplaneSpecificationTest {

	@Test
	public void testGetSize() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, Collections.<Double, Double> emptyMap() );
		assertSame(AirplaneSize.SMALL, spec.getSize());
	}

	@Test
	public void testGetMaxRange() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, Collections.<Double, Double> emptyMap() );
		assertTrue(100 == spec.getMaxRange());
	}

	@Test
	public void testGetMaxPayload() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, Collections.<Double, Double> emptyMap() );
		assertTrue(2 == spec.getMaxPayload());
	}

	@Test
	public void testGetSeats() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, Collections.<Double, Double> emptyMap() );
		assertTrue(1 == spec.getSeats());
	}

	@Test
	public void testHasConsumptionData() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Double, Double>() );
		assertFalse(spec.hasConsumptionData());
		
		spec.addConsumptionEntry(10d, 100d);
		assertTrue(spec.hasConsumptionData());
	}

	@Test
	public void testGetConsumptionProfile() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Double, Double>() );
		spec.addConsumptionEntry(10d, 100d);
		spec.addConsumptionEntry(10d, 150d);
		spec.addConsumptionEntry(20d, 200d);
		assertTrue(2 == spec.getConsumptionProfile().size());
		assertTrue(150d == spec.getConsumptionProfile().get(10d));
		assertTrue(200d == spec.getConsumptionProfile().get(20d));
		
	}

	@Test
	public void testAddConsumptionEntry() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Double, Double>() );
		spec.addConsumptionEntry(10d, 100d);
		assertTrue(spec.hasConsumptionData());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddConsumptionEntryNullKey() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Double, Double>() );
		spec.addConsumptionEntry(null, 100d);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddConsumptionEntryNullValue() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Double, Double>() );
		spec.addConsumptionEntry(10d, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddConsumptionEntryNegKey() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Double, Double>() );
		spec.addConsumptionEntry(-10d, 100d);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAddConsumptionEntryNegValue() {
		AirplaneSpecification spec = new AirplaneSpecification(AirplaneSize.SMALL, 1, 100, 2, new HashMap<Double, Double>() );
		spec.addConsumptionEntry(10d, -100d);
	}
	
	@Test
	public void testAirplaneSpecificationOtherConstructor(){
		AirplaneSpecification spec = new AirplaneSpecification(StandardModel.P_747_400);
		assertTrue("747 should be a big airplane.", spec.getSize() == AirplaneSize.BIG);
		assertTrue("747 should have more than 0 seats.", spec.getSeats() > 0);
		assertTrue("Range of 747 should be bigger than 0.", spec.getMaxRange() > 0);
		assertTrue("747 should have a bigger payload capacity than 0", spec.getMaxPayload() > 0);
		assertTrue("Standard model should have a consumption profile", spec.hasConsumptionData());
		assertTrue("Standard model should have a consumption profile", spec.getConsumptionProfile().size() > 0);
		
		
	}

}
