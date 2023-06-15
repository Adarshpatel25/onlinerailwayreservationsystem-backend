package com.casestudy.railwayreservationsystem.dto;

public class PassengerDetails {

	private String passengerName;
	private int passengerAge;
	private int seatNo;
	
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
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	
	public PassengerDetails(String passengerName, int passengerAge, int seatNo) {
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.seatNo = seatNo;
	}
	public PassengerDetails() {
		
	}
	
	@Override
	public String toString() {
		return "PassengerDetails [passengerName=" + passengerName + ", passengerAge=" + passengerAge + ", seatNo="
				+ seatNo + "]";
	}
	
}
