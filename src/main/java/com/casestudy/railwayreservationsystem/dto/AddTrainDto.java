package com.casestudy.railwayreservationsystem.dto;


public class AddTrainDto {

	private String trainName;
	private String fromStation;
	private String toStation;
	private String startTiming;
	
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getFromStation() {
		return fromStation;
	}
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	public String getToStation() {
		return toStation;
	}
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	public String getStartTiming() {
		return startTiming;
	}
	public void setStartTiming(String startTiming) {
		this.startTiming = startTiming;
	}

	
	public AddTrainDto(String trainName, String fromStation, String toStation, String startTiming) {
		this.trainName = trainName;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.startTiming = startTiming;
	}
	
	public AddTrainDto() {
		
	}
	
	
}
