package edu.northeastern.cs5200.controllers;

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
import edu.northeastern.cs5200.service.BookingService;
import edu.northeastern.cs5200.util.ResponseResource;

@RestController
@RequestMapping("/api")
public class BookingController {
	
	//TODO convert to post
	private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private BookingService bookingService;
	
	// TODO authentication
	@RequestMapping(value = { "/book/itinerary" }, method = RequestMethod.GET)
	@ResponseBody
	ResponseResource bookItinerary() {
		FlightSearchResult itinerary = new FlightSearchResult();
		return bookingService.bookItinerary(itinerary);
	}

}
