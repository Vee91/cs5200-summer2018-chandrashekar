package edu.northeastern.cs5200.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5200.dao.AdminDao;
import edu.northeastern.cs5200.dto.AdminFlight;
import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.dto.Flight;
import edu.northeastern.cs5200.dto.Passenger;
import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.util.ResponseResource;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public ResponseResource getUsers() {
		ResponseResource out = new ResponseResource();
		out.setCode("200");
		out.setSuccess("users", adminDao.getUsers());
		return out;
	}

	@Transactional
	@Override
	public ResponseResource register(Person person) {
		ResponseResource out = new ResponseResource();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(person.getPassword());
		person.setPassword(hashedPassword);
		adminDao.insertPerson(person);
		if (person.getRole().equals("USER")) {
			adminDao.insertUser(person.getId());
		} else if (person.getRole().equals("EMPLOYEE")) {
			adminDao.insertEmployee(person.getId());
		}
		out.setCode("200");
		out.setMessage("User created successfully");
		return out;
	}

	@Override
	public ResponseResource update(Person person) {
		ResponseResource out = new ResponseResource();
		adminDao.updateProfile(person);
		out.setCode("200");
		out.setMessage("Profile updated successfully");
		return out;
	}

	@Override
	public ResponseResource getflights() {
		ResponseResource out = new ResponseResource();
		List<AdminFlight> flights = adminDao.getFlights();
		out.setCode("200");
		out.setSuccess("flights", flights);
		return out;
	}

	@Override
	public ResponseResource autoComplete(String value) {
		ResponseResource resp = new ResponseResource();
		resp.setCode("200");
		resp.setSuccess("airports", adminDao.autoComplete(value));
		return resp;
	}

	@Override
	public ResponseResource autoCompleteAirline(String value) {
		ResponseResource resp = new ResponseResource();
		resp.setCode("200");
		resp.setSuccess("airlines", adminDao.autoCompleteAirline(value));
		return resp;
	}

	@Override
	public ResponseResource autoCompleteAircraft(String value) {
		ResponseResource resp = new ResponseResource();
		resp.setCode("200");
		resp.setSuccess("aircrafts", adminDao.autoCompleteAircraft(value));
		return resp;
	}

	@Override
	public ResponseResource addFlight(AdminFlight flight) {
		ResponseResource out = new ResponseResource();
		adminDao.insertFlight(flight);
		out.setCode("200");
		out.setMessage("Flight created successfully");
		return out;
	}

	@Override
	public ResponseResource updateFlight(AdminFlight flight) {
		ResponseResource out = new ResponseResource();
		adminDao.updateFlight(flight);
		out.setCode("200");
		out.setMessage("Profile updated successfully");
		return out;
	}

	@Override
	public ResponseResource addCard(CreditCard card) {
		ResponseResource out = new ResponseResource();
		adminDao.insertCard(card);
		out.setCode("200");
		out.setMessage("Card created successfully");
		return out;
	}

	@Override
	public ResponseResource getBookings() {
		ResponseResource out = new ResponseResource();
		List<Integer> bookings = adminDao.getBookings();
		out.setCode("200");
		out.setSuccess("flights", bookings);
		return out;
	}

	@Override
	public ResponseResource addPassengerToBooking(int bookingId, Passenger pass) {
		ResponseResource out = new ResponseResource();
		adminDao.insertPassengerToDB(bookingId, pass);
		out.setCode("200");
		out.setMessage("Card created successfully");
		return out;
	}

	@Override
	public ResponseResource getSchedules() {
		ResponseResource out = new ResponseResource();
		List<Flight> schedules = adminDao.getSchedules();
		out.setCode("200");
		out.setSuccess("flights", schedules);
		return out;
	}

	@Override
	public ResponseResource getEmployees() {
		ResponseResource out = new ResponseResource();
		List<String> schedules = adminDao.getEmployees();
		out.setCode("200");
		out.setSuccess("employees", schedules);
		return out;
	}

	@Override
	public ResponseResource assignCrew(int scheduleId, String employee) {
		ResponseResource out = new ResponseResource();
		adminDao.assignCrew(scheduleId, employee);
		out.setCode("200");
		out.setMessage("Crew added successfully");
		return out;
	}

}
