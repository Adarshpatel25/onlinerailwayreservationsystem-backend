package com.casestudy.railwayreservationsystem.dto;


import java.util.List;

public class MyBookings {

	private String email;
	private long pnrNo;
	private long trainNo;
	private String trainName;
	private String fromStation;
	private String toStation;
	private List<PassengerDetails> passengerDetails;
	private double amount;
	private String seatCoach;		
	private String time;
	private String date;
	private String reservationDate;
	private boolean isCancelled;
	

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(long pnrNo) {
		this.pnrNo = pnrNo;
	}
	public long getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(long trainNo) {
		this.trainNo = trainNo;
	}
	public String getSeatCoach() {
		return seatCoach;
	}
	public void setSeatCoach(String seatCoach) {
		this.seatCoach = seatCoach;
	}
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<PassengerDetails> getPassengerDetails() {
		return passengerDetails;
	}
	public void setPassengerDetails(List<PassengerDetails> passengerDetails) {
		this.passengerDetails = passengerDetails;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public boolean getIsCancelled() {
		return isCancelled;
	}
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	
	public MyBookings(String email, long pnrNo, long trainNo, String trainName, String fromStation, String toStation,
			List<PassengerDetails> passengerDetails, double amount, String seatCoach, String time, String date,
			String reservationDate, boolean isCancelled) {
		super();
		this.email = email;
		this.pnrNo = pnrNo;
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.passengerDetails = passengerDetails;
		this.amount = amount;
		this.seatCoach = seatCoach;
		this.time = time;
		this.date = date;
		this.reservationDate = reservationDate;
		this.isCancelled = isCancelled;
	}
	public MyBookings() {
		super();
	}
	
	
	@Override
	public String toString() {
		return "MyBookings [email=" + email + ", pnrNo=" + pnrNo + ", trainNo=" + trainNo + ", trainName=" + trainName
				+ ", fromStation=" + fromStation + ", toStation=" + toStation + ", passengerDetails=" + passengerDetails
				+ ", amount=" + amount + ", seatCoach=" + seatCoach + ", time=" + time + ", date=" + date
				+ ", reservationDate=" + reservationDate + ", isCancelled=" + isCancelled + "]";
	}
	

}
