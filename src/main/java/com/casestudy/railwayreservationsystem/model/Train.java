package com.casestudy.railwayreservationsystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Train {

	@Id
	private long trainNo;
	
	private String trainName;

	private String fromStation;
	
	private String toStation;
	
	@OneToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="train")
	private List<Seat> seat;
	
	@OneToMany(mappedBy="train")
	private List<SeatReservation> seatReservation;

	@OneToMany(mappedBy="train", cascade = CascadeType.ALL)
	private List<TrainDestination> trainDestination;
	

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

	public List<SeatReservation> getSeatReservation() {
		return seatReservation;
	}

	public void setSeatReservation(List<SeatReservation> seatReservation) {
		this.seatReservation = seatReservation;
	}

	
	public List<TrainDestination> getTrainDestination() {
		return trainDestination;
	}

	public void setTrainDestination(List<TrainDestination> trainDestination) {
		this.trainDestination = trainDestination;
	}
	

	public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
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

	public Train(long trainNo, String trainName, String fromStation, String toStation, List<Seat> seat,
			List<SeatReservation> seatReservation, List<TrainDestination> trainDestination) {
		super();
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.seat = seat;
		this.seatReservation = seatReservation;
		this.trainDestination = trainDestination;
	}

	public Train() {
	
	}
	
}
