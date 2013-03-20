package org.eaticious.greenlicious.vessels;

import static org.junit.Assert.*;

import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Unit;
import org.eaticious.greenlicious.vessels.TruckSpecification.TruckModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TruckTest {

	private static Truck truck40000;

	private static Truck truck7500;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		truck40000 = new Truck(TruckModel.TR_40000);
		truck7500 = new Truck(TruckModel.TR_7500);
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
	public void testGetTotalCO2e() {
		Double expected;
		Double distance;
		Double loadFactor;
		Double emptyTripFactor;

		// setting parameters
		distance = 6325d;
		loadFactor = 0.75;
		emptyTripFactor = 0.5;

		expected = truck40000.getTotalCO2ePerKM(loadFactor, emptyTripFactor).getAmount() * distance;
		assertEquals(expected, truck40000.getTotalCO2e(distance, loadFactor, emptyTripFactor).getAmount());

		expected = truck7500.getTotalCO2ePerKM(loadFactor, emptyTripFactor).getAmount() * distance;
		assertEquals(expected, truck7500.getTotalCO2e(distance, loadFactor, emptyTripFactor).getAmount());
	}

	@Test
	public void testGetTotalCO2ePerKM() {

		Double expected;
		Double actual;
		Double loadFactor;
		Double emptyTripFactor;

		// setting parameters & test
		loadFactor = 0.75;
		emptyTripFactor = 0.5;

		// test 1
		expected = 1.120395; // taken from calculations in excel sheet / EcoTransit
		actual = Math.round(truck40000.getTotalCO2ePerKM(loadFactor, emptyTripFactor).getAmount() * 1000000) / 1000000d;
		assertEquals(expected, actual);

		// test 2
		expected = 0.513358; // taken from calculations in excel sheet / EcoTransit
		actual = Math.round(truck7500.getTotalCO2ePerKM(loadFactor, emptyTripFactor).getAmount() * 1000000) / 1000000d;
		assertEquals(expected, actual);
	}

	@Test
	public void testGetCO2e() {
		Double expected;
		Double actual;
		Quantity payload;
		Double distance;
		Double loadFactor;
		Double emptyTripFactor;
		
		loadFactor = 0.8;
		emptyTripFactor = 0.4;
		distance = 3819d;
		payload = new QuantityImpl(12d, Unit.TON);

		expected = truck40000.getCO2ePerKM(payload, loadFactor, emptyTripFactor).getAmount() * distance;
		actual = truck40000.getCO2e(payload, distance, loadFactor, emptyTripFactor).getAmount();
		assertEquals(expected, actual);
		
		payload.setAmount(3d);
		
		expected = truck7500.getCO2ePerKM(payload, loadFactor, emptyTripFactor).getAmount() * distance;
		actual = truck7500.getCO2e(payload, distance, loadFactor, emptyTripFactor).getAmount();
		assertEquals(expected, actual);

	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testGetCO2e_Exception() {
		Quantity payload = new QuantityImpl(12d, Unit.LITRE);
		truck40000.getCO2e(payload, 12d, 0.5, 0.5);
	}

	@Test
	public void testGetCO2ePerKM() {
		Double expected;
		Double actual;
		Quantity payload;
		Double loadFactor;
		Double emptyTripFactor;
		
		loadFactor = 0.8;
		emptyTripFactor = 0.4;
		payload = new QuantityImpl(753d, Unit.KILOGRAM);

		expected = 0.041956;
		actual = Math.round(truck40000.getCO2ePerKM(payload, loadFactor, emptyTripFactor).getAmount() * 1000000) / 1000000d;
		assertEquals(expected, actual);
		
		payload.setAmount(512d);
		
		expected = 0.094556;
		actual = Math.round(truck7500.getCO2ePerKM(payload, loadFactor, emptyTripFactor).getAmount() * 1000000) / 1000000d;
		assertEquals(expected, actual);

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetCO2ePerKM_Exception() {
		Quantity payload = new QuantityImpl(753d, Unit.CENTILITRE);
		truck40000.getCO2ePerKM(payload, 0.8, 0.5);
	}

}
