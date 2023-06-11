package com.casestudy.railwayreservationsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pnrNo;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="transaction")
	@JoinColumn(name="pnr_no")
	private SeatReservation seatReservation;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id")
	private Users user;
	
	private double amount;
	
	private String transactionTime;
	private String transactionDate;

	public SeatReservation getSeatReservation() {
		return seatReservation;
	}

	public void setSeatReservation(SeatReservation seatReservation) {
		this.seatReservation = seatReservation;
	}

	public Users getUser() {
		return user;
	}

	public void setPnrNo(long pnrNo) {
		this.pnrNo = pnrNo;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public long getPnrNo() {
		return pnrNo;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Transaction(int pnrNo, SeatReservation seatReservation, Users user, double amount, String transactionDate) {
	
		this.pnrNo = pnrNo;
		this.seatReservation = seatReservation;
		this.user = user;
		this.amount = amount;
		this.transactionDate = transactionDate;
	}

	public Transaction() {
	
	}
	
}
