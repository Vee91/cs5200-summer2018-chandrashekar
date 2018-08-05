package edu.northeastern.cs5200.dto;

public class FlightSearch {

	private String origin;
	private String destination;
	private String departureDate;
	private String returnDate;
	private boolean returnFlight;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReturnFlight() {
		return returnFlight;
	}

	public void setReturnFlight(boolean returnFlight) {
		this.returnFlight = returnFlight;
	}

}
