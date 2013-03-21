package org.eaticious.greenlicious.vessels;

public class ElectricTrainSpecification {
	
	public enum TrainModel{
		EUROPEAN_STANDARD(1000), INTERNATIONAL_BIG(4000);
		
		private Integer gtw; 
		
		private TrainModel(Integer gtw){
			this.gtw = gtw;
		}
		
		public Integer getGTW(){
			return this.gtw;
		}
	}

	private Integer gtw;
	
	public ElectricTrainSpecification(TrainModel model){
		this(model.getGTW());
	}
	
	public ElectricTrainSpecification(Integer gtw){
		this.gtw = gtw;
	}
	
	public Integer getGTW(){
		return this.gtw;
	}
	

}