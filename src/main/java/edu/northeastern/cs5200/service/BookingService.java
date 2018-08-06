package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.dto.FlightSearchResult;
import edu.northeastern.cs5200.util.ResponseResource;

public interface BookingService {
	
	public ResponseResource bookItinerary(FlightSearchResult itinerary);

}
