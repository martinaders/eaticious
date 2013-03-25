package org.eaticious.greenlicious.vessels;

import org.eaticious.common.FoodProduct.TransportClass;
import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Unit;
import org.eaticious.greenlicious.vessels.BargeSpecification.BargeModel;

public class Barge implements Vessel {
	
	private BargeSpecification specs;

	public Barge(BargeModel model) {
		this(new BargeSpecification(model));
	}

	public Barge(BargeSpecification bargeSpecification) {
		this.specs = bargeSpecification;
	}

	public Quantity getCO2ePerKGKM(TransportClass tc, boolean ecoTransitCorrection) {
		return this.specs.getCO2ePerKGKM(tc, ecoTransitCorrection);
	}

	public Quantity getCO2e(Quantity distance, Quantity weight, TransportClass tc, boolean ecoTransitCorrection) {
		Double calcDistance = distance.convert(Unit.KILOMETER).getAmount();
		Double calcWeight = weight.convert(Unit.KILOGRAM).getAmount();
		Double amount =  this.getCO2ePerKGKM(tc, ecoTransitCorrection).getAmount() * calcDistance * calcWeight;
		return new QuantityImpl(amount, Unit.KG_CO2E);
	}

}
