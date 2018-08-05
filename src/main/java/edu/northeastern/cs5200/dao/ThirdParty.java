package edu.northeastern.cs5200.dao;

import java.util.List;

import edu.northeastern.cs5200.dto.Airport;
import edu.northeastern.cs5200.dto.FlightSearch;

public interface ThirdParty {

	public List<Airport> autoComplete(String value);

	public String searchFlight(FlightSearch query);
	
	public String getAirport(String code);
}
