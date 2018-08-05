package edu.northeastern.cs5200.service;

import java.util.List;

import edu.northeastern.cs5200.dto.Airport;
import edu.northeastern.cs5200.dto.FlightSearch;
import edu.northeastern.cs5200.dto.FlightSearchResult;
import edu.northeastern.cs5200.util.ResponseResource;

public interface SearchService {

	public ResponseResource autoComplete(String value);

	public ResponseResource searchFlight(FlightSearch query);
}
