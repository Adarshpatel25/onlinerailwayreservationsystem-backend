package com.casestudy.railwayreservationsystem.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class TrainDestination {
	

	@Id
	private int trainDestination_id;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="train_no")
	private Train train;
	
	private int sequence_no;
	
	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="destination_id")
	private Destination destination;
	
	private String destination_timing;
	private String destination_name;
	
	
	public String getDestination_name() {
		return destination_name;
	}

	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}

	
	public int getTrainDestination_id() {
		return trainDestination_id;
	}

	public void setTrainDestination_id(int trainDestination_id) {
		this.trainDestination_id = trainDestination_id;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public int getSequence_no() {
		return sequence_no;
	}

	public void setSequence_no(int sequence_no) {
		this.sequence_no = sequence_no;
	}
	
	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	public String getDestination_timing() {
		return destination_timing;
	}

	public void setDestination_timing(String destination_timing) {
		this.destination_timing = destination_timing;
	}
	

	public TrainDestination(int trainDestination_id, Train train, int sequence_no, Destination destination,
			String destination_timing, String destination_name) {
		super();
		this.trainDestination_id = trainDestination_id;
		this.train = train;
		this.sequence_no = sequence_no;
		this.destination = destination;
		this.destination_timing = destination_timing;
		this.destination_name = destination_name;
	}

	public TrainDestination() {

	}
	
}
