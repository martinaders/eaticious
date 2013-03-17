package org.eaticious.greenlicious;

public interface RegionSpecification {

	/**
	 * used to describe the typical landscape of a region, used mainly when calculating co2e from commercial transport
	 */
	public enum Landscape {
		
		FLAT(0.9), HILL(1.0), MOUNTAIN(1.1), NONE(1.0);
		
		/**
		 * the factor for the energy consumption of an electrical train in this Landscape
		 */
		private double electricalTrainFactor;

		private Landscape(double trainFactor) {
			this.electricalTrainFactor = trainFactor;
		}

		/**
		 * 
		 * @return the factor for the energy consumption of an electrical train in this Landscape
		 */
		public double getElectricalTrainFactor() {
			return this.electricalTrainFactor;
		}
	}

	/**
	 * Returns the normal gross ton weight of train wagons in this region
	 * 
	 * international average: 1500[t]; USA, Canada, Australia, China: 4000[t])
	 * 
	 * @return The standard gross ton weight of train wagons in this region
	 */
	public int getTrainGTW();

	/**
	 * Returns the typical Landscape of this Region
	 * 
	 * @return The typical Landscape of this Region or Lanscape.NONE if unknown
	 */
	public Landscape getLandscape();

}
