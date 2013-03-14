package org.eaticious.greenlicious;

public interface TransportationDetails {

	/**
	 * Returns the normal gross ton weight of train wagons in this region
	 * (international: 1000; USA, Canada, Australia, China: 4000)
	 * 
	 * @return The standard gross ton weight of train wagons in this region
	 */
	int getTrainGTW();

	

}
