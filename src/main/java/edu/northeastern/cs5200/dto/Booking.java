package edu.northeastern.cs5200.dto;

import java.util.List;

public class Booking {

	private int bookingId;
	private List<BookedFlight> flights;
	private List<Passenger> passengers;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public List<BookedFlight> getFlights() {
		return flights;
	}

	public void setFlights(List<BookedFlight> flights) {
		this.flights = flights;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

}
