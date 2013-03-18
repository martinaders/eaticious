package org.eatlicious.greenlicious.vessels;

import java.util.HashMap;
import java.util.Map;

public class AirplaneSpecification implements Vessel {
	
	public enum StandardModels {
		F_747_400, F_767_300F, F_737_200C, P_FOKKER100, P_757_200, P_747_400;
	}
	
	public enum AirplaneSize{
		SMALL, MEDIUM, BIG;
	}
	
	private Map<Integer, Double> consumptionProfile;
	private Integer maxRange;
	private Integer maxPayload;
	private Integer seats;
	private AirplaneSize size;
	
	public AirplaneSpecification(){
		this.consumptionProfile = new HashMap<Integer, Double>();
		this.maxRange = 1;
	}
	
	public AirplaneSize getSize(){
		return this.size;
	}
	
	public Integer getMaxRange(){
		return this.maxRange;
	}
	
	public Integer getMaxPayload(){
		return this.maxPayload;
	}
	
	public Integer getSeats(){
		return this.seats;
	}

	public boolean hasConsumptionProfile() {
		return !this.consumptionProfile.isEmpty();
	}

	public Map<Integer, Double> getConsumptionProfile() {
		return this.consumptionProfile;
	}
	
	public void setConsumptionEntry(Integer distance, Double value){
		this.consumptionProfile.put(distance, value);
	}

}
