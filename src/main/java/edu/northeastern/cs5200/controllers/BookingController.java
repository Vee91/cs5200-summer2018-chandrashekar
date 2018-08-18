package edu.northeastern.cs5200.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.dto.CardWrapper;
import edu.northeastern.cs5200.dto.FlightSearchResult;
import edu.northeastern.cs5200.dto.SearchSessionObject;
import edu.northeastern.cs5200.dto.Wrapper;
import edu.northeastern.cs5200.service.BookingService;
import edu.northeastern.cs5200.util.ResponseResource;

@RestController
@RequestMapping("/api")
public class BookingController {

	private static final Logger LOG = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;
	
	@Autowired 
	private SearchSessionObject sessionObject; 

	@RequestMapping(value = { "/saveSession" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseResource saveSession(@RequestBody FlightSearchResult query) {
		sessionObject.setInbound(query.getInbound());
		sessionObject.setOutbound(query.getOutbound());
		sessionObject.setPrice(query.getPrice());
		sessionObject.setTax(query.getTax());
		ResponseResource out = new ResponseResource();
		out.setCode("200");
		return out;
	}
	
	@RequestMapping(value = { "/getSession" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseResource getSession() {
		ResponseResource out = new ResponseResource();
		FlightSearchResult f = new FlightSearchResult();
		f.setInbound(sessionObject.getInbound());
		f.setOutbound(sessionObject.getOutbound());
		f.setPrice(sessionObject.getPrice());
		f.setTax(sessionObject.getTax());
		out.setCode("200");
		out.setSuccess("query", f);
		return out;
	}
	
	@RequestMapping(value = { "/book/itinerary" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseResource bookItinerary(@RequestBody Wrapper map) {
		return bookingService.bookItinerary(map.getItinerary(), map.getPassengers(), map.getCard());
	}
	
	@RequestMapping(value = { "/book/itinerary/card" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseResource bookItineraryWithCard(@RequestBody CardWrapper map) {
		return bookingService.bookItinerary(map.getItinerary(), map.getPassengers(), map.getCardId());
	}
	
	@RequestMapping(value = { "/itinerary" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseResource getMyItineraries() {
		return bookingService.getMyItineraries();
	}
	
	@RequestMapping(value = { "/ticket/cancel" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseResource cancel(@RequestBody int id) {
		return bookingService.cancel(id);
	}

}
