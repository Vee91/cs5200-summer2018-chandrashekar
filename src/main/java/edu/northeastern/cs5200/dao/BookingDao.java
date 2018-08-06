package edu.northeastern.cs5200.dao;

import java.util.List;

import edu.northeastern.cs5200.dto.Flight;
import edu.northeastern.cs5200.dto.Itinerary;

public interface BookingDao {

	public boolean checkIfExists(Flight f);

	public boolean checkIfExists(String origin, String destination, String duration);

	public int insertBooking(List<Flight> flights, String username);

	public void insertItinerary(Itinerary itinerary);

	public void scheduleFlights(List<Flight> flights);
}
