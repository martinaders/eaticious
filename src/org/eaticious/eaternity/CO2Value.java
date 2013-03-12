package org.eaticious.eaternity;

import org.eaticious.common.*;


//co2 equivalent in g, splitted up by all the different quotas/factors
public interface CO2Value{
	
	public Quantity getProductionValue();
	public Quantity getConservationValue();
	public Quantity getTransportationValue();
	public Quantity getNoFactorsValue();
	public Quantity getTotalValue();
	

	public CO2Value add(CO2Value other);

	public CO2Value mult(Double factor);

}