package org.eaticious.greenlicious;

import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Unit;

/**
 * @deprecated Needs to be changed...
 * @author Sven Peetz
 *
 */
public enum Vessel {

	// TODO check lf and etf
	FOOT(0d, 100, 50, NORMALIZATION_TYPE.PER_KM),
	BIKE(0d, 100, 50, NORMALIZATION_TYPE.PER_KM),
	CAR_DIESEL_SMALL(1d, 100, 50, NORMALIZATION_TYPE.PER_KM), // TODO add co2e
	CAR_DIESEL_MID(1d, 100, 50, NORMALIZATION_TYPE.PER_KM), // TODO add co2e
	CAR_DIESEL_BIG(1d, 100, 50, NORMALIZATION_TYPE.PER_KM), // TODO add co2e
	CAR_PETROL_SMALL(1d, 100, 50, NORMALIZATION_TYPE.PER_KM), // TODO add co2e
	CAR_PETROL_MID(1d, 100, 50, NORMALIZATION_TYPE.PER_KM), // TODO add co2e
	CAR_PETROL_BIG(1d, 100, 50, NORMALIZATION_TYPE.PER_KM), // TODO add co2e
	CAR_ELECTRIC(1d, 100, 50, NORMALIZATION_TYPE.PER_KM), // TODO add co2e
	PUBLIC_TRANSPORT(1d, 100, 50, NORMALIZATION_TYPE.PER_KM), // TODO add co2e
	// CO2e-values below per km AND kg of transported good, at LF 100% and EF 0%. (extracted from ecotransit)
	TRANSPORTER_BELOW_7500(0.0001218569, 30, 10, NORMALIZATION_TYPE.PER_KG_AND_KM), //7,5t - 12t
	TRANSPORTER_7500_TO_12000(0.0001005803, 30, 10, NORMALIZATION_TYPE.PER_KG_AND_KM), //7,5t - 12t 
	TRANSPORTER_12000_TO_24000(0.0000618956, 30, 10, NORMALIZATION_TYPE.PER_KG_AND_KM), // 12t - 24t
	TRANSPORTER_24000_TO_40000(0.0000444874, 30, 10, NORMALIZATION_TYPE.PER_KG_AND_KM), // 24t - 40t
	TRANSPORTER_40000_TO_60000(0.0000425532, 30, 10, NORMALIZATION_TYPE.PER_KG_AND_KM), // 40t - 60t
	TRAIN_ELECTRIC(0.0000130435, 30, 20, NORMALIZATION_TYPE.PER_KG_AND_KM),
	TRAIN_DIESEL(0.0000189036, 30, 20, NORMALIZATION_TYPE.PER_KG_AND_KM),
	SHIP_OCEAN(0.0000055658, 100, 65, NORMALIZATION_TYPE.PER_KG_AND_KM),
	SHIP_BARGE(0.0000316935, 100, 70, NORMALIZATION_TYPE.PER_KG_AND_KM),
	AIRPLANE_SHORT_HAUL(0.0014858, 65, 0, NORMALIZATION_TYPE.PER_KG_AND_KM), // including RFI-factor, TODO check as co2e value differs when choosing different locations
	AIRPLANE_AVG_HAUL(0.0014162028, 65, 0, NORMALIZATION_TYPE.PER_KG_AND_KM), // including RFI-factor, TODO check as co2e value differs when choosing different locations
	AIRPLANE_LONG_HAUL(0.0014456114, 65, 0, NORMALIZATION_TYPE.PER_KG_AND_KM); // including RFI-factor, TODO check as co2e value differs when choosing different locations
	
	private Double co2eAvg;

	private Double stdLF;
	
	private Double stdEF;
	
	private NORMALIZATION_TYPE type;
	
	private enum NORMALIZATION_TYPE{
		PER_KM, PER_KG_AND_KM
	}
	
	private Vessel(Double co2e, Integer avgLoadfactor, Integer emptyTripFactor, NORMALIZATION_TYPE type){
		this.co2eAvg = co2e;
		this.stdLF = avgLoadfactor / 100.0;
		this.stdEF = emptyTripFactor / 100.0;
		this.type = type;
	}
	
	/**
	 * 
	 * @param distance The distance traveled with this vessel
	 * @return The complete CO2e-emission in kg per km for the transport of 1kg of goods over the given distance
	 */
	public CO2eSample calculateFreightEmissions(Double distance){
		return this.calculateFreightEmissions(distance, new QuantityImpl(1d, Unit.KILOGRAM), this.stdLF, this.stdEF, Unit.KILOGRAM);
	}
	
	
	public CO2eSample calculateFreightEmissions(Double distance, Quantity amount){
		return this.calculateFreightEmissions(distance, amount, this.stdLF, this.stdEF, Unit.KILOGRAM);
	}
	
	public CO2eSample calculateFreightEmissions(Double distance, Quantity amount, Double loadFactor, Double emptyTripFactor, Unit unit){
		if(!unit.isMassUnit()){
			throw new IllegalArgumentException("The Unit passed as a parameter has be of kind \"MASS\"");
		}
		
		if(loadFactor < 0 || loadFactor > 1){
			throw new IllegalArgumentException("The loadFactor has to be between 0 and 1");
		}
		
		if(emptyTripFactor < 0 || emptyTripFactor > 1){
			throw new IllegalArgumentException("The emptyTripFactor has to be between 0 and 1");
		}
		
		CO2eSample sample = new CO2eSampleImpl(unit);
		Double emission = null;
		
		switch (this.type) {
		case PER_KG_AND_KM:
			// TODO incorporate EF
			emission = (distance * this.co2eAvg / (1-loadFactor)) * amount.convert(Unit.KILOGRAM).getAmount();
			break;
		case PER_KM:
			// TODO add calculation
			break;

		default:
			break;
		}
		// TODO convert to desired unit
		sample.setMeanValue(emission);
		return sample;
	}
	
}
