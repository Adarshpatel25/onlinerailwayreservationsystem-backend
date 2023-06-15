package com.casestudy.railwayreservationsystem.dto;


public class TrainSchedule {

	private int destinationNo;
	private String destinationName;
	private String destinationTime;
	
	public int getDestinationNo() {
		return destinationNo;
	}
	public void setDestinationNo(int destinationNo) {
		this.destinationNo = destinationNo;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getDestinationTime() {
		return destinationTime;
	}
	public void setDestinationTime(String destinationTime) {
		this.destinationTime = destinationTime;
	}
	
	
	public TrainSchedule(int destinationNo, String destinationName, String destinationTime) {
		this.destinationNo = destinationNo;
		this.destinationName = destinationName;
		this.destinationTime = destinationTime;
	}
	
	public TrainSchedule() {
		
	}
	
}
