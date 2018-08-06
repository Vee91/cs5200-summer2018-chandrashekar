package edu.northeastern.cs5200.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.dao.BookingDao;
import edu.northeastern.cs5200.dto.Flight;
import edu.northeastern.cs5200.dto.FlightSearchResult;
import edu.northeastern.cs5200.dto.Itinerary;
import edu.northeastern.cs5200.util.ResponseResource;

@Service
public class BookingServiceImpl implements BookingService {

	private static final Logger LOG = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	private BookingDao bookingDao;

	@Override
	public ResponseResource bookItinerary(FlightSearchResult itinerary) {
		// TODO validations

		int bookingid = validateOutbound(itinerary.getOutbound(), true);
		// TODO insert passengers
		// bookingDao.insertPassengers(bookingid, passengers)

		if (itinerary.getInbound() != null) {
			int inboundBookingid = validateOutbound(itinerary.getInbound(), false);
			// TODO insert passengers
			// bookingDao.insertPassengers(bookingid, passengers)
		}
		// TODO
		// inserttransaction
		ResponseResource out = new ResponseResource();
		out.setCode("200");
		out.setMessage("Sucessfully booked your itinerary");
		return out;
	}

	private int validateOutbound(Itinerary itinerary, boolean outbound) {
		List<Flight> unscheduled = new ArrayList<>();
		List<Flight> flights = itinerary.getFlights();

		// check if the flights are already scheduled in our database
		Iterator<Flight> outItr = flights.iterator();
		while (outItr.hasNext()) {
			Flight f = outItr.next();
			if (!bookingDao.checkIfExists(f)) {
				unscheduled.add(f);
			}
		}

		// if flights are not scheduled scheduled, schedule them
		if (!unscheduled.isEmpty()) {
			bookingDao.scheduleFlights(unscheduled);
		}

		// insert itenerary if it does not exist
		handleItinerary(itinerary, outbound);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return bookingDao.insertBooking(itinerary.getFlights(), username);

	}

	private void handleItinerary(Itinerary itinerary, boolean outbound) {
		boolean itrExists = isItineraryPresent(itinerary, outbound);

		// if itinerary does not exist then make it
		if (!itrExists) {
			bookingDao.insertItinerary(itinerary);
		}
	}

	private boolean isItineraryPresent(Itinerary itinerary, boolean outbound) {
		if (outbound) {
			return bookingDao.checkIfExists(itinerary.getOrigin(), itinerary.getDestination(), itinerary.getDuration());
		} else {
			return bookingDao.checkIfExists(itinerary.getDestination(), itinerary.getOrigin(), itinerary.getDuration());
		}
	}

}
