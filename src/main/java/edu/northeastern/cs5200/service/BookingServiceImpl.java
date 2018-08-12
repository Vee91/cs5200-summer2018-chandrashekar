package edu.northeastern.cs5200.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5200.dao.BookingDao;
import edu.northeastern.cs5200.dao.CreditCardDao;
import edu.northeastern.cs5200.dto.Booking;
import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.dto.Flight;
import edu.northeastern.cs5200.dto.FlightSearchResult;
import edu.northeastern.cs5200.dto.Itinerary;
import edu.northeastern.cs5200.dto.Passenger;
import edu.northeastern.cs5200.util.ResponseResource;

@Service
public class BookingServiceImpl implements BookingService {

	private static final Logger LOG = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private CreditCardDao creditDao;

	@Transactional
	@Override
	public ResponseResource bookItinerary(FlightSearchResult itinerary, List<Passenger> passengers, CreditCard card) {
		// TODO validations
		LOG.info("BookingService | bookItinerary | Successful :: Validation Success");
		ResponseResource out = new ResponseResource();
		int inboundBookingid = -1;
		LOG.info("BookingService | bookItinerary | starting outbound booking");
		int bookingid = validateOutbound(itinerary.getOutbound(), true);
		LOG.info("BookingService | bookItinerary | outbound booking successful with booking id " + bookingid);
		int outboundPass = bookingDao.insertPassengers(bookingid, passengers);
		LOG.info("BookingService | bookItinerary | " + outboundPass + "passengers added to outbound booking "
				+ bookingid);
		if (outboundPass != passengers.size()) {
			LOG.error("BookingService | bookItinerary | Error adding passengers for outbound booking " + bookingid);
			out.setCode("400");
			out.setMessage("There was an error adding passengers to your booking. Please try again");
			return out;
		}

		if (itinerary.getInbound() != null) {
			LOG.info("BookingService | bookItinerary | starting inbound booking");
			inboundBookingid = validateOutbound(itinerary.getInbound(), false);
			LOG.info("BookingService | bookItinerary | inbound booking successful with booking id " + inboundBookingid);
			int inboundPass = bookingDao.insertPassengers(inboundBookingid, passengers);
			LOG.info("BookingService | bookItinerary | " + inboundPass + "passengers added to inbound booking "
					+ inboundBookingid);
			if (inboundPass != passengers.size()) {
				LOG.error("BookingService | bookItinerary | Error adding passengers for outbound booking "
						+ inboundBookingid);
				out.setCode("400");
				out.setMessage("There was an error adding passengers to your booking. Please try again");
				return out;
			}
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		creditDao.addCreditCard(card, username);

		out.setCode("200");
		out.setMessage("Sucessfully booked your itinerary");
		return out;
	}

	@Transactional
	@Override
	public ResponseResource bookItinerary(FlightSearchResult itinerary, List<Passenger> passengers, int cardId,
			int securityCode) {
		// TODO validations
		LOG.info("BookingService | bookItinerary | Successful :: Validation Success");
		ResponseResource out = new ResponseResource();
		int inboundBookingid = -1;
		LOG.info("BookingService | bookItinerary | starting outbound booking");
		int bookingid = validateOutbound(itinerary.getOutbound(), true);
		LOG.info("BookingService | bookItinerary | outbound booking successful with booking id " + bookingid);
		int outboundPass = bookingDao.insertPassengers(bookingid, passengers);
		LOG.info("BookingService | bookItinerary | " + outboundPass + "passengers added to outbound booking "
				+ bookingid);
		if (outboundPass != passengers.size()) {
			LOG.error("BookingService | bookItinerary | Error adding passengers for outbound booking " + bookingid);
			out.setCode("400");
			out.setMessage("There was an error adding passengers to your booking. Please try again");
			return out;
		}

		if (itinerary.getInbound() != null) {
			LOG.info("BookingService | bookItinerary | starting inbound booking");
			inboundBookingid = validateOutbound(itinerary.getInbound(), false);
			LOG.info("BookingService | bookItinerary | inbound booking successful with booking id " + inboundBookingid);
			int inboundPass = bookingDao.insertPassengers(inboundBookingid, passengers);
			LOG.info("BookingService | bookItinerary | " + inboundPass + "passengers added to inbound booking "
					+ inboundBookingid);
			if (inboundPass != passengers.size()) {
				LOG.error("BookingService | bookItinerary | Error adding passengers for outbound booking "
						+ inboundBookingid);
				out.setCode("400");
				out.setMessage("There was an error adding passengers to your booking. Please try again");
				return out;
			}
		}

		if (validateTransaction(bookingid, cardId, securityCode) == 0) {
			out.setCode("400");
			out.setMessage(
					"There was a problem while charging your card. Your card will not be charged. Please try aggin.");
			return out;
		}

		if (inboundBookingid != -1) {
			if (validateTransaction(inboundBookingid, cardId, securityCode) == 0) {
				out.setCode("400");
				out.setMessage(
						"There was a problem while charging your card. Your card will not be charged. Please try aggin.");
				return out;
			}
		}

		out.setCode("200");
		out.setMessage("Sucessfully booked your itinerary");
		return out;
	}

	private int validateTransaction(int bookingid, int cardId, int securityCode) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		int dbSecCode = bookingDao.getSecurityCode(cardId, username);
		if (dbSecCode == securityCode) {
			return bookingDao.makeTransaction(bookingid, cardId);
		} else {
			return 0;
		}
	}

	private int validateOutbound(Itinerary itinerary, boolean outbound) {
		List<Flight> unscheduled = new ArrayList<>();
		List<Flight> flights = itinerary.getFlights();

		// check if the flights are already scheduled in our database
		Iterator<Flight> outItr = flights.iterator();
		while (outItr.hasNext()) {
			Flight f = outItr.next();
			if (!bookingDao.checkIfFlightExist(f)) {
				bookingDao.insertFlight(f);
			}
			if (!bookingDao.checkIfExists(f)) {
				unscheduled.add(f);
			} else {
				f.setScheduleId(bookingDao.getScheduleId(f));
			}
		}

		// if flights are not scheduled scheduled, schedule them
		if (!unscheduled.isEmpty()) {
			LOG.info("BookingService | validateOutbound | outbound :: " + outbound + " | unscheduled flights exist");
			bookingDao.scheduleFlights(unscheduled);
			LOG.info("BookingService | validateOutbound | outbound :: " + outbound + " | scheduled flights");
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
			LOG.info("BookingService | handleItinerary | outbound :: " + outbound + " | itinerary does not exist");
			bookingDao.insertItinerary(itinerary);
			LOG.info("BookingService | handleItinerary | outbound :: " + outbound + " | itinerary added successfully");
		}
	}

	private boolean isItineraryPresent(Itinerary itinerary, boolean outbound) {
		if (outbound) {
			return bookingDao.checkIfExists(itinerary.getOrigin(), itinerary.getDestination(), itinerary.getDuration());
		} else {
			return bookingDao.checkIfExists(itinerary.getDestination(), itinerary.getOrigin(), itinerary.getDuration());
		}
	}

	@Override
	public ResponseResource getMyItineraries() {
		ResponseResource out = new ResponseResource();
		Map<String, Object> success = new HashMap<>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		List<Booking> active = bookingDao.getActiveBookingId(username);
		List<Booking> inactive = bookingDao.getInactiveBookingId(username);

		Iterator<Booking> activeItr = active.iterator();
		Iterator<Booking> inactiveItr = inactive.iterator();

		while (activeItr.hasNext()) {
			Booking b = activeItr.next();
			bookingDao.getBookedFlights(b, username, true);
			bookingDao.getBookedPassengers(b);
		}

		while (inactiveItr.hasNext()) {
			Booking b = inactiveItr.next();
			bookingDao.getBookedFlights(b, username, true);
			bookingDao.getBookedPassengers(b);
		}

		success.put("active", active);
		success.put("inactive", inactive);
		out.setSuccess(success);
		return out;
	}

}
