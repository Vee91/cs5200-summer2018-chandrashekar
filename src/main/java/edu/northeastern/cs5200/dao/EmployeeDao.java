package edu.northeastern.cs5200.dao;

import java.util.List;

import edu.northeastern.cs5200.dto.BookedFlight;

public interface EmployeeDao {

	public List<BookedFlight> getFlights(String username);

}
