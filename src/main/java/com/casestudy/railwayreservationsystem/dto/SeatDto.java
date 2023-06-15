package com.casestudy.railwayreservationsystem.dto;

public class SeatDto {

	private int seatNo;
	private String seatCoach;
	
	
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public String getSeatCoach() {
		return seatCoach;
	}
	public void setSeatCoach(String seatCoach) {
		this.seatCoach = seatCoach;
	}
	
	public SeatDto(int seatNo, String seatCoach) {
		super();
		this.seatNo = seatNo;
		this.seatCoach = seatCoach;
	}
	
	public SeatDto() {
	
	}
	
}
