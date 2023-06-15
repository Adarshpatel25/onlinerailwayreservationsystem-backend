package com.casestudy.railwayreservationsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int passengerId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "seat_no")
	private Seat seat;
	
	
	@ManyToOne
	@JoinColumn(name="pnr_no")
	private SeatReservation seatReservation;
	
	private String passengerName;
	
	private int passengerAge;

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public SeatReservation getSeatReservation() {
		return seatReservation;
	}

	public void setSeatReservation(SeatReservation seatReservation) {
		this.seatReservation = seatReservation;
	}

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

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}


	public Passenger(int passengerId, Users user, Seat seat, SeatReservation seatReservation, String passengerName,
			int passengerAge) {
		this.passengerId = passengerId;
		this.user = user;
		this.seat = seat;
		this.seatReservation = seatReservation;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
	}

	public Passenger() {
		
	}
	
	
	
}
