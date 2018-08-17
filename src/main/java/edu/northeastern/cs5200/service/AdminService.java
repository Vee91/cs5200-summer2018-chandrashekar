package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.dto.AdminFlight;
import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.dto.Passenger;
import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.util.ResponseResource;

public interface AdminService {

	public ResponseResource getUsers();

	public ResponseResource register(Person p);

	public ResponseResource update(Person person);

	public ResponseResource getflights();

	public ResponseResource autoComplete(String value);

	public ResponseResource autoCompleteAirline(String value);

	public ResponseResource autoCompleteAircraft(String value);

	public ResponseResource addFlight(AdminFlight flight);

	public ResponseResource updateFlight(AdminFlight flight);

	public ResponseResource addCard(CreditCard card);

	public ResponseResource getBookings();

	public ResponseResource addPassengerToBooking(int bookingId, Passenger pass);

	public ResponseResource getSchedules();

	public ResponseResource getEmployees();

	public ResponseResource assignCrew(int scheduleId, String employee);

}
