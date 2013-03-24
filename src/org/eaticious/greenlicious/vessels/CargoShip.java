package org.eaticious.greenlicious.vessels;

import org.eaticious.common.FoodProduct.TransportClass;
import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Unit;

public class CargoShip implements Vessel {

	public enum ShippingRoute {
		GLOBAL_AVERAGE(1d), GLOBAL(1.083333333), INTRA_CONTINENTAL(1.148809524), INTRA_CONTINENTAL_EU(1.380952381), 
		PANAMA(0.994047619), TRANSPACIFIC(0.976190476), TRANSATLANTIC(0.994047619), SUEZ(0.857142857);

		private Double emissionFactor;

		private ShippingRoute(Double emissionFactor) {
			this.emissionFactor = emissionFactor;
		}

		public Double getEmissionFactor() {
			return this.emissionFactor;
		}
	}
	
	private static Double KGCO2ePerTEUKM = 0.168;

	public Quantity getCO2e(Quantity weight, TransportClass tc, Quantity distance, ShippingRoute route) {
		final Double factor = distance.convert(Unit.KILOMETER).getAmount() * weight.convert(Unit.KILOGRAM).getAmount();
		final Quantity co2e = this.getCO2ePerKGKM(tc, route);
		co2e.setAmount(co2e.getAmount() * factor);
		
		return co2e;
	}

	public Quantity getCO2ePerKGKM(TransportClass tc, ShippingRoute route) {
		final Double co2ePerKGKM = KGCO2ePerTEUKM * route.getEmissionFactor() / (tc.getTonsPerTEUKM() * 1000d);
		return new QuantityImpl(co2ePerKGKM, Unit.KG_CO2E);
	}

}
