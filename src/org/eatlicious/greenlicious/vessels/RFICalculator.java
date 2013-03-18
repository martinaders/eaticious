package org.eatlicious.greenlicious.vessels;

import java.util.Map;
import java.util.TreeMap;

public class RFICalculator {

	private static Map<Integer, Double> rfiValues;

	static {
		// values taken from EcoTransIT (2012)
		rfiValues = new TreeMap<Integer, Double>();
		rfiValues.put(500, 1d);
		rfiValues.put(750, 1.81);
		rfiValues.put(1000, 2.18);
		rfiValues.put(2000, 2.52);
		rfiValues.put(4000, 2.73);
		rfiValues.put(10000, 2.87);
	}

	/**
	 * Returns the RFI-factor for airtravels.
	 * This factor is used to calculate the higher GHG-potential of GHG-emissions when emitted near the border of the atmosphere.
	 * The factor is calculated using the distance of a flight assuming that long distance flights are traveling in higher regions than short distance flights.
	 * Values taken from ECOTransIT (2012)
	 * 
	 * @param distance The distance of the airtravel
	 * @return The rfi factor for the airtravel
	 */
	public static double getRFIFactor(Integer distance) {
		double result = 1d;
		if (distance <= 0) {
			throw new IllegalArgumentException("The distance has to be bigger than 0, was " + distance.toString());
		}

		if (rfiValues.containsKey(distance)) {
			result = rfiValues.get(distance);
		} else {
			Integer minKey = null;
			Integer maxKey = null;

			for (Integer key : rfiValues.keySet()) {
				int diff = distance - key;
				if(diff < 0){
					// Since TreeMap is sorted by keys and the actual key is bigger than the distance, maxKey is found and the loop will be stopped
					maxKey = key;
					break;
				} else {
					// key is smaller than distance but bigger than the previous key
					minKey = key;
				}
			}
			
			if(minKey == null){
				// Could happen if distance is smaller than smallest defined key
				result = rfiValues.get(maxKey);
			} else {
				// linear interpolation
				double minValue = rfiValues.get(minKey);
				double steep = (rfiValues.get(maxKey) - minValue) / (maxKey - minKey);
				result = minValue + steep * (distance - minValue);
			}
		}

		return result;
	}
}
