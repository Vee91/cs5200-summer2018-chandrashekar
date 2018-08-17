package edu.northeastern.cs5200.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.dto.AdminFlight;
import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.dto.Passenger;
import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.service.AdminService;
import edu.northeastern.cs5200.util.ResponseResource;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
   	@ResponseBody
   	public ResponseResource getUsers() {
   		return adminService.getUsers();
   	}
	
	@RequestMapping(value = { "/add/user" }, method = RequestMethod.POST)
   	@ResponseBody
   	public ResponseResource addUser(@RequestBody Person p) {
   		return adminService.register(p);
   	}

	@RequestMapping(value = { "/update/user" }, method = RequestMethod.POST)
   	@ResponseBody
   	public ResponseResource updateUser(@RequestBody Person person) {
   		return adminService.update(person);
   	}
	
	@RequestMapping(value = { "/flights" }, method = RequestMethod.GET)
   	@ResponseBody
   	public ResponseResource getflights() {
   		return adminService.getflights();
   	}
	
	@RequestMapping(value = { "/autocomplete/{value}" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseResource autoComplete(@PathVariable(value = "value") String value) {
		return adminService.autoComplete(value);
	}
	
	@RequestMapping(value = { "/autocomplete/airline/{value}" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseResource autoCompleteAirline(@PathVariable(value = "value") String value) {
		return adminService.autoCompleteAirline(value);
	}
	
	@RequestMapping(value = { "/autocomplete/aircraft/{value}" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseResource autoCompleteAircraft(@PathVariable(value = "value") String value) {
		return adminService.autoCompleteAircraft(value);
	}
	
	@RequestMapping(value = { "/add/flight" }, method = RequestMethod.POST)
   	@ResponseBody
   	public ResponseResource addFlight(@RequestBody AdminFlight flight) {
   		return adminService.addFlight(flight);
   	}
	
	@RequestMapping(value = { "/update/flight" }, method = RequestMethod.POST)
   	@ResponseBody
   	public ResponseResource updateUser(@RequestBody AdminFlight flight) {
		return adminService.updateFlight(flight);
   	}
	
	@RequestMapping(value = { "/add/card" }, method = RequestMethod.POST)
   	@ResponseBody
   	public ResponseResource addCard(@RequestBody CreditCard card) {
   		return adminService.addCard(card);
   	}
	
	@RequestMapping(value = { "/bookings" }, method = RequestMethod.GET)
   	@ResponseBody
   	public ResponseResource getBookings() {
   		return adminService.getBookings();
   	}
	
	@RequestMapping(value = { "/booking/{value}/passenger" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseResource addPassengerToBooking(@PathVariable(value = "value") int bookingId, @RequestBody Passenger pass) {
		return adminService.addPassengerToBooking(bookingId, pass);
	}
	
	@RequestMapping(value = { "/schedule" }, method = RequestMethod.GET)
   	@ResponseBody
   	public ResponseResource getSchedules() {
   		return adminService.getSchedules();
   	}
	
	@RequestMapping(value = { "/employees" }, method = RequestMethod.GET)
   	@ResponseBody
   	public ResponseResource getEmployees() {
   		return adminService.getEmployees();
   	}
	
	@RequestMapping(value = { "/schedule/{value}/crew" }, method = RequestMethod.POST)
   	@ResponseBody
   	public ResponseResource assignCrew(@PathVariable(value = "value") int scheduleId, @RequestBody String employee) {
   		return adminService.assignCrew(scheduleId, employee);
   	}
}
