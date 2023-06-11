package com.casestudy.railwayreservationsystem.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Destination{

	@Id
	private int destination_id;
	
	private String destination_name;
	
	@JsonIgnore
	@OneToMany(mappedBy="destination", cascade = CascadeType.ALL)
	private List<TrainDestination> trainDestination;
	

	public int getDestination_id() {
		return destination_id;
	}

	public void setDestination_id(int destination_id) {
		this.destination_id = destination_id;
	}

	public String getDestination_name() {
		return destination_name;
	}

	public void setDestination_name(String destination_name) {
		this.destination_name = destination_name;
	}
	
	public List<TrainDestination> getTrainDestination() {
		return trainDestination;
	}

	public void setTrainDestination(List<TrainDestination> trainDestination) {
		this.trainDestination = trainDestination;
	}

	public Destination(int destination_id, String destination_name, List<TrainDestination> trainDestination) {
		super();
		this.destination_id = destination_id;
		this.destination_name = destination_name;
		this.trainDestination = trainDestination;
	}

	public Destination() {
		
	}

	
}
