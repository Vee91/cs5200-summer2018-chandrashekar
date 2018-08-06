package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.dto.FlightSearch;
import edu.northeastern.cs5200.util.ResponseResource;

public interface SearchService {

	public ResponseResource autoComplete(String value);

	public ResponseResource searchFlight(FlightSearch query);
}
