package edu.northeastern.cs5200.controllers;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.dto.FlightSearchResult;
import edu.northeastern.cs5200.dto.Passenger;
import edu.northeastern.cs5200.service.BookingService;
import edu.northeastern.cs5200.util.ResponseResource;

@RestController
@RequestMapping("/api")
public class BookingController {

	// TODO convert to post
	private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;

	// TODO authentication
	@RequestMapping(value = { "/book/itinerary" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseResource bookItinerary() {
		// TODO
		FlightSearchResult itinerary = new FlightSearchResult();
		List<Passenger> passengers = new ArrayList<>();
		int cardId = 0;
		int securityCode = 0;
		return bookingService.bookItinerary(itinerary, passengers, cardId, securityCode);
	}

}
