package com.casestudy.railwayreservationsystem.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Seat {

	@Id
	private int seatId;
	
	@ManyToOne
	@JoinColumn(name="seat_reservation_id")
	private SeatReservation seatReservation;
	
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="train_no")
	private Train train;
	
	private int seatNumber;
	
	private String seatType;
	
	private String seatCoach;
	
	private boolean isAvailable;

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	
	public String getSeatCoach() {
		return seatCoach;
	}

	public void setSeatCoach(String seatCoach) {
		this.seatCoach = seatCoach;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Seat(int seatId, Train train, int seatNumber, String seatType, String seatCoach, boolean isAvailable) {
		super();
		this.seatId = seatId;
		this.train = train;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
		this.seatCoach = seatCoach;
		this.isAvailable = isAvailable;
	}

	public Seat() {
	
	}
	
}
