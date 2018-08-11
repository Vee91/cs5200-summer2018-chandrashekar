package edu.northeastern.cs5200.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.dto.FlightSearchResult;
import edu.northeastern.cs5200.dto.Passenger;
import edu.northeastern.cs5200.dto.Wrapper;
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
	@RequestMapping(value = { "/book/itinerary" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseResource bookItinerary(@RequestBody Wrapper map) {
		return bookingService.bookItinerary(map.getItinerary(), map.getPassengers(), map.getCard());
	}

}
