package org.eaticious.greenlicious.vessels;

import static org.junit.Assert.*;

import org.eaticious.common.FoodProduct.TransportClass;
import org.eaticious.greenlicious.RegionSpecification.Landscape;
import org.eaticious.greenlicious.vessels.ElectricTrainSpecification.TrainModel;
import org.junit.BeforeClass;
import org.junit.Test;

public class ElectricTrainTest {

	private static ElectricTrain stdTrain;
	private static ElectricTrain bigTrain;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		stdTrain = new ElectricTrain(TrainModel.EUROPEAN_STANDARD);
		bigTrain = new ElectricTrain(TrainModel.INTERNATIONAL_BIG);
	}

	@Test
	public void testGetFuelConsumption() {
		Landscape landscape;
		TransportClass tc;
		Double expected;
		Double actual;

		landscape = Landscape.HILL;
		tc = TransportClass.BULK;
		expected = 0.00002761;
		actual = Math.round(stdTrain.getFuelConsumption(landscape, tc).getAmount() * 100000000) / 100000000d;
		assertEquals(expected, actual);

		landscape = Landscape.FLAT;
		expected = 0.00002485;
		actual = Math.round(stdTrain.getFuelConsumption(landscape, tc).getAmount() * 100000000) / 100000000d;
		assertEquals(expected, actual);
		
		landscape = Landscape.MOUNTAIN;
		expected = 0.00003037;
		actual = Math.round(stdTrain.getFuelConsumption(landscape, tc).getAmount() * 100000000) / 100000000d;
		assertEquals(expected, actual);
		
		tc = TransportClass.AVERAGE;
		expected = 0.00003504;
		actual = Math.round(stdTrain.getFuelConsumption(landscape, tc).getAmount() * 100000000) / 100000000d;
		assertEquals(expected, actual);
		
		tc = TransportClass.UNKNOWN;
		expected = 0.00003504;
		actual = Math.round(stdTrain.getFuelConsumption(landscape, tc).getAmount() * 100000000) / 100000000d;
		assertEquals(expected, actual);
		
		tc = TransportClass.VOLUME;
		expected = 0.00004555;
		actual = Math.round(stdTrain.getFuelConsumption(landscape, tc).getAmount() * 100000000) / 100000000d;
		assertEquals(expected, actual);
	}

}
