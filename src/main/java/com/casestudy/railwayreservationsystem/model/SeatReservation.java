package com.casestudy.railwayreservationsystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class SeatReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pnrNo;

	@JsonIgnore
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private Users user;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Passenger> passenger;

	@NotFound(action=NotFoundAction.IGNORE)
	@OneToOne(cascade=CascadeType.ALL)
	private Transaction transaction;
	
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="seat_no", referencedColumnName = "seatNo")
	private Seat seat;
	
	private String trainStatus;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="train_no")
	private Train train;
	
	private String trainTiming;
	private String trainDate;
	private String fromStation;
	private String toStation;
	
	private String trainName;
	private boolean isCancelled;


	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}

	
	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public String getTrainStatus() {
		return trainStatus;
	}

	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	public List<Passenger> getPassenger() {
		return passenger;
	}
	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	public long getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(long pnrNo) {
		this.pnrNo = pnrNo;
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

	public Train getTrain() {
		return train;
	}

	public void setTrainNo(Train train) {
		this.train = train;
	}
	
	public String getTrainTiming() {
		return trainTiming;
	}

	public void setTrainTiming(String trainTiming) {
		this.trainTiming = trainTiming;
	}

	public String getTrainDate() {
		return trainDate;
	}

	public void setTrainDate(String trainDate) {
		this.trainDate = trainDate;
	}
	
	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public SeatReservation(long pnrNo, Users user, List<Passenger> passenger, Transaction transaction, Seat seat,
			 String trainStatus, Train train, String trainTiming,
			String trainDate, String fromStation, String toStation, String trainName, boolean isCancelled) {
		super();
		this.pnrNo = pnrNo;
		this.user = user;
		this.passenger = passenger;
		this.transaction = transaction;
		this.seat = seat;
	
		this.trainStatus = trainStatus;
		this.train = train;
		this.trainTiming = trainTiming;
		this.trainDate = trainDate;
		this.fromStation = fromStation;
		this.toStation = toStation;
		this.trainName = trainName;
		this.isCancelled = isCancelled;
	}
	public SeatReservation() {

	}

}
