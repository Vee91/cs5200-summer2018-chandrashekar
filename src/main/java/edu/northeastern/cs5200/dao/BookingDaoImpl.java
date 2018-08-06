package edu.northeastern.cs5200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.dto.Flight;
import edu.northeastern.cs5200.dto.Itinerary;
import edu.northeastern.cs5200.queryconstants.QueryConstants;
import edu.northeastern.cs5200.util.DateUtil;

@Repository
public class BookingDaoImpl implements BookingDao {

	private static final Logger LOG = LoggerFactory.getLogger(BookingDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean checkIfExists(Flight f) {
		int x = jdbcTemplate.queryForObject(
				QueryConstants.SCHEDULE_EXIST.toString(), new Object[] { f.getFlightNumber(),
						DateUtil.dateToSQLDate(f.getDepartsAt()), DateUtil.dateToSQLDate(f.getDepartsAt()) },
				Integer.class);
		return x > 0 ? true : false;
	}

	@Override
	public boolean checkIfExists(String origin, String destination, String duration) {
		int x = jdbcTemplate.queryForObject(QueryConstants.ITINERARY_EXIST.toString(),
				new Object[] { origin, destination, duration }, Integer.class);
		return x > 0 ? true : false;
	}

	// if inserting only into booking take schedule id of flights
	// and insert in booking
	@Override
	public int insertBooking(List<Flight> flights, String username) {
		
		int bookingid = insertIntoBookingDetails(username);
		Iterator<Flight> itr = flights.iterator();
		while (itr.hasNext()) {
			int scid = getScheduleId(itr.next());
			confirmBooking(scid, bookingid);
		}
		return bookingid;
	}

	private void confirmBooking(int scid, int bookingid) {
		jdbcTemplate.update(QueryConstants.CONFIRM_BOOKING.toString(), new Object[] { bookingid, scid },
				new int[] { Types.INTEGER, Types.INTEGER });
	}

	private int insertIntoBookingDetails(String username) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(QueryConstants.INSERT_BOOKING_DETAILS.toString(),
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, username);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	private int getScheduleId(Flight next) {
		return jdbcTemplate.queryForObject(QueryConstants.GET_SCHEDULE_ID.toString(),
				new Object[] { next.getFlightNumber() }, Integer.class);
	}

	@Override
	public void insertItinerary(Itinerary itinerary) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(QueryConstants.INSERT_ITINERARY.toString(),
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, itinerary.getOrigin());
				ps.setString(2, itinerary.getDestination());
				ps.setString(3, itinerary.getDuration());
				return ps;
			}
		}, keyHolder);
		int itid = keyHolder.getKey().intValue();
		assignFlightsToItinerary(itinerary.getFlights(), itid);
	}

	private void assignFlightsToItinerary(List<Flight> flights, int itid) {
		Iterator<Flight> itr = flights.iterator();
		while (itr.hasNext()) {
			jdbcTemplate.update(QueryConstants.ASSIGN_FLIGHT_ITINERARY.toString(),
					new Object[] { itid, getScheduleId(itr.next()) }, new int[] { Types.INTEGER, Types.INTEGER });
		}
	}

	@Override
	public void scheduleFlights(List<Flight> flights) {
		Iterator<Flight> itr = flights.iterator();
		while (itr.hasNext()) {
			Flight f = itr.next();
			jdbcTemplate.update(QueryConstants.INSERT_SCHEDULE.toString(),
					new Object[] { f.getFlightNumber(), DateUtil.dateToSQLDate(f.getDepartsAt()),
							DateUtil.dateToSQLDate(f.getArrivesAt()) },
					new int[] { Types.INTEGER, Types.DATE, Types.DATE });
		}
	}

}
