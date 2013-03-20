package org.eaticious.greenlicious.vessels;

public class TruckSpecification {
	
	public enum TruckModel{
		TR_60000(22000, 38000, 27.1, 52.3), TR_40000(14000, 26000, 22.7, 37.1), TR_24000(12000, 12000, 19.3, 23.5), TR_12000(6000, 6000, 16.9, 20.0), TR_7500(4000, 3500, 13.0, 14.4); 
		
		private Integer emptyWeight;
		private Integer payloadCapacity;
		private Double consumptionEmpty;
		private Double consumptionFull;
		
		private TruckModel(Integer emptyWeight, Integer payloadCapacity, Double consumptionEmpty, Double consumptionFull){
			this.emptyWeight = emptyWeight;
			this.payloadCapacity = payloadCapacity;
			this.consumptionEmpty = consumptionEmpty;
			this.consumptionFull = consumptionFull;
		}
		
		public Integer getEmptyWeight(){
			return this.emptyWeight;
		}
		
		public Integer getPayloadCapacity(){
			return this.payloadCapacity;
		}
		
		public Double getFuelConsumptionEmpty(){
			return this.consumptionEmpty;
		}
		
		public Double getFuelConsumptionFull(){
			return this.consumptionFull;
		}
	}

	private Integer emptyWeight;
	private Integer payloadCapacity;
	private Double consumptionEmpty;
	private Double consumptionFull;
	
	public TruckSpecification(TruckModel model){
		this(model.getEmptyWeight(), model.getPayloadCapacity(), model.getFuelConsumptionEmpty(), model.getFuelConsumptionFull());
	}
	
	public TruckSpecification(Integer emptyWeight, Integer payloadCapacity, Double consumptionEmpty, Double consumptionFull){
		this.emptyWeight = emptyWeight;
		this.payloadCapacity = payloadCapacity;
		this.consumptionEmpty = consumptionEmpty;
		this.consumptionFull = consumptionFull;
	}
	
	public Integer getEmptyWeight(){
		return this.emptyWeight;
	}
	
	public Integer getPayloadCapacity(){
		return this.payloadCapacity;
	}
	
	public Double getFuelConsumptionEmpty(){
		return this.consumptionEmpty;
	}
	
	public Double getFuelConsumptionFull(){
		return this.consumptionFull;
	}
}
