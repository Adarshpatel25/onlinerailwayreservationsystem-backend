package com.casestudy.railwayreservationsystem.dto;


public class TrainDto {

	private long trainNo;
	private String trainName;
	private String fromStationTiming;
	private String toStationTiming;
	private String fromStation;
	private String toStation;
	
	public long getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(long trainNo) {
		this.trainNo = trainNo;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getFromStationTiming() {
		return fromStationTiming;
	}
	public void setFromStationTiming(String fromStationTiming) {
		this.fromStationTiming = fromStationTiming;
	}
	public String getToStationTiming() {
		return toStationTiming;
	}
	public void setToStationTiming(String toStationTiming) {
		this.toStationTiming = toStationTiming;
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
	public TrainDto(long trainNo, String trainName) {
		this.trainNo = trainNo;
		this.trainName = trainName;
	}
	public TrainDto() {

	}
	
	@Override
	public String toString() {
		return "TrainDto [trainNo=" + trainNo + ", trainName=" + trainName + ", fromStationTiming=" + fromStationTiming
				+ ", toStationTiming=" + toStationTiming + ", fromStation=" + fromStation + ", toStation=" + toStation
				+ "]";
	}

	
}
