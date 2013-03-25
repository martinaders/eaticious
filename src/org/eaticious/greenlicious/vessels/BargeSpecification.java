package org.eaticious.greenlicious.vessels;

import java.util.HashMap;
import java.util.Map;

import org.eaticious.common.FoodProduct.TransportClass;
import org.eaticious.common.Quantity;
import org.eaticious.common.QuantityImpl;
import org.eaticious.common.Unit;

public class BargeSpecification {

	public enum BargeModel {
		BARGE_STD(0.00006123, 0.00006123, 0.00006123, 0.80058154),

		BARGE_CLASS_V(0.00003811, 0.00003811, 0.00003811, 0.90038639),

		BARGE_CONTAINER_STD(0.00003852, 0.00005320, 0.00009310, 1.25050020),

		BARGE_CONTAINER_CLASS_V(0.00002303, 0.00003180, 0.00005565, 1.24787861);

		private Double co2eBulk;
		private Double co2eAvg;
		private Double co2eVolume;
		private double ecotransitFactor;

		private BargeModel(Double co2eBulk, Double co2eAVG, Double co2eVolume,
				Double ecotransitFactor) {
			this.co2eBulk = co2eBulk;
			this.co2eAvg = co2eAVG;
			this.co2eVolume = co2eVolume;
			this.ecotransitFactor = ecotransitFactor;
		}

		public Double getCO2e(TransportClass tc) {
			Double result;
			switch (tc) {
			case BULK:
				result = this.co2eBulk;
				break;
			case VOLUME:
				result = this.co2eVolume;
				break;
			case AVERAGE:
			default:
				result = this.co2eAvg;
				break;
			}
			return result;
		}

		public Double getEcoTransitCorrectionFactor() {
			return this.ecotransitFactor;
		}
	}

	private Map<TransportClass, Double> co2eMap;
	private Double ecotransitFactor;

	public BargeSpecification(BargeModel model) {
		this(model.getCO2e(TransportClass.BULK), model.getCO2e(TransportClass.AVERAGE),
				model.getCO2e(TransportClass.VOLUME), model.getEcoTransitCorrectionFactor());
	}

	public BargeSpecification(Double co2eBulk, Double co2eAvg, Double co2eVolume,
			Double ecotransitFactor) {
		this.co2eMap = new HashMap<TransportClass, Double>();
		co2eMap.put(TransportClass.BULK, co2eBulk);
		co2eMap.put(TransportClass.AVERAGE, co2eAvg);
		co2eMap.put(TransportClass.VOLUME, co2eVolume);
		this.ecotransitFactor = ecotransitFactor;
	}
	
	public Quantity getCO2ePerKGKM(TransportClass tc, boolean useEcoTransitCorrection){
		Quantity result = new QuantityImpl();
		result.setUnit(Unit.KG_CO2E);
		Double co2e = useEcoTransitCorrection ? this.co2eMap.get(tc) * this.ecotransitFactor : this.co2eMap.get(tc);
		result.setAmount(co2e);
		return result;
	}

}
