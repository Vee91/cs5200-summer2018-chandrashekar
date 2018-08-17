package edu.northeastern.cs5200.dao;

import java.util.List;

import edu.northeastern.cs5200.dto.AdminFlight;
import edu.northeastern.cs5200.dto.Aircraft;
import edu.northeastern.cs5200.dto.Airline;
import edu.northeastern.cs5200.dto.Airport;
import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.dto.Passenger;
import edu.northeastern.cs5200.dto.Person;

public interface AdminDao {

	public List<Person> getUsers();

	public void insertPerson(Person person);

	public void insertUser(int id);

	public void insertEmployee(int id);

	public void updateProfile(Person person);

	public List<AdminFlight> getFlights();

	public List<Airport> autoComplete(String value);

	public List<Airline> autoCompleteAirline(String value);

	public List<Aircraft> autoCompleteAircraft(String value);

	public void insertFlight(AdminFlight flight);

	public void updateFlight(AdminFlight flight);

	public void insertCard(CreditCard card);

	public List<Integer> getBookings();

	public void insertPassengerToDB(int bookingId, Passenger pass);

}
