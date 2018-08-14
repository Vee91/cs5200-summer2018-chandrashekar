package edu.northeastern.cs5200.service;

import java.util.List;

import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.dto.FlightSearchResult;
import edu.northeastern.cs5200.dto.Passenger;
import edu.northeastern.cs5200.util.ResponseResource;

public interface BookingService {
	
	public ResponseResource bookItinerary(FlightSearchResult itinerary, List<Passenger> passengers, int cardId);

	public ResponseResource bookItinerary(FlightSearchResult itinerary, List<Passenger> passengers, CreditCard card);

	public ResponseResource getMyItineraries();

	public ResponseResource cancel(int id);

}
