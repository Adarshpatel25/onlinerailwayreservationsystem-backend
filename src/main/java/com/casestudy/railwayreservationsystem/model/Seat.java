package com.casestudy.railwayreservationsystem.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
public class Seat implements Serializable {

	@Id
	private int seatId;
	
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="train_no")
	private Train train;
	
	private int seatNo;
	
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

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
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

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Seat(int seatId, Train train, int seatNo, String seatType, String seatCoach, boolean isAvailable) {
		super();
		this.seatId = seatId;
		this.train = train;
		this.seatNo = seatNo;
		this.seatType = seatType;
		this.seatCoach = seatCoach;
		this.isAvailable = isAvailable;
	}

	public Seat() {
		super();
	}
	
}
