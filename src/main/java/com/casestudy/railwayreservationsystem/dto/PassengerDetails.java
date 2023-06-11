package com.casestudy.railwayreservationsystem.dto;

public class PassengerDetails {

	private String passengerName;
	private int passengerAge;
	
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getPassengerAge() {
		return passengerAge;
	}
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	
	public PassengerDetails(String passengerName, int passengerAge) {
		super();
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
	}
	
	public PassengerDetails() {
		super();
	}
	@Override
	public String toString() {
		return "PassengerDetails [passengerName=" + passengerName + ", passengerAge=" + passengerAge + "]";
	}
	
}
