package com.casestudy.railwayreservationsystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	
	@OneToMany(mappedBy="user")
	private List<Passenger> passengers;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<SeatReservation> seatReservation;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<Transaction> transaction;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;

	private int age;
	private String gender;
	
	private boolean isAdmin;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	public List<SeatReservation> getSeatReservation() {
		return seatReservation;
	}
	public void setSeatReservation(List<SeatReservation> seatReservation) {
		this.seatReservation = seatReservation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public Users(int user_id, List<Passenger> passengers, List<SeatReservation> seatReservation,
			List<Transaction> transaction, String name, String email, String password, int age, String gender,
			boolean isAdmin) {
		super();
		this.user_id = user_id;
		this.passengers = passengers;
		this.seatReservation = seatReservation;
		this.transaction = transaction;
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.isAdmin = isAdmin;
	}
	public Users() {
		
	}
	
}
