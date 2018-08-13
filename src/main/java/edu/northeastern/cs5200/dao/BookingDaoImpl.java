package edu.northeastern.cs5200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.dto.BookedFlight;
import edu.northeastern.cs5200.dto.Booking;
import edu.northeastern.cs5200.dto.Flight;
import edu.northeastern.cs5200.dto.Itinerary;
import edu.northeastern.cs5200.dto.Passenger;
import edu.northeastern.cs5200.queryconstants.QueryConstants;
import edu.northeastern.cs5200.util.DateUtil;

@Repository
public class BookingDaoImpl implements BookingDao {

	private static final Logger LOG = LoggerFactory.getLogger(BookingDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean checkIfExists(Flight f) {
		int x = jdbcTemplate.queryForObject(QueryConstants.SCHEDULE_EXIST.toString(),
				new Object[] { f.getFlightNumber(), DateUtil.dateToSQLDate(f.getDepartsAt()),
						DateUtil.dateToSQLDate(f.getArrivesAt()) },
				new int[] { Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP }, Integer.class);
		return x > 0 ? true : false;
	}

	@Override
	public boolean checkIfFlightExist(Flight f) {
		int x = jdbcTemplate.queryForObject(QueryConstants.FLIGHT_EXIST.toString(), new Object[] { f.getFlightNumber(),
				f.getOrigin().getValue(), f.getDestination().getValue(), f.getAirline(), f.getAircraft() },
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
			confirmBooking(itr.next().getScheduleId(), bookingid);
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

	@Override
	public int getScheduleId(Flight next) {
		return jdbcTemplate.queryForObject(
				QueryConstants.GET_SCHEDULE_ID.toString(), new Object[] { next.getFlightNumber(),
						DateUtil.dateToSQLDate(next.getDepartsAt()), DateUtil.dateToSQLDate(next.getArrivesAt()) },
				Integer.class);
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
					new Object[] { itid, itr.next().getScheduleId() }, new int[] { Types.INTEGER, Types.INTEGER });
		}
	}

	@Override
	public void scheduleFlights(List<Flight> flights) {
		Iterator<Flight> itr = flights.iterator();
		while (itr.hasNext()) {
			Flight f = itr.next();
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(QueryConstants.INSERT_SCHEDULE.toString(),
							Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, f.getFlightNumber());
					ps.setTimestamp(2, DateUtil.dateToSQLDate(f.getDepartsAt()));
					ps.setTimestamp(3, DateUtil.dateToSQLDate(f.getArrivesAt()));
					return ps;
				}
			}, keyHolder);
			f.setScheduleId(keyHolder.getKey().intValue());
		}
	}

	@Override
	public int insertPassengers(int bookingid, List<Passenger> passengers) {
		Iterator<Passenger> itr = passengers.iterator();
		int i = 0;
		while (itr.hasNext()) {
			i += insertPassengerToDB(bookingid, itr.next());
		}
		return i;
	}

	private int insertPassengerToDB(int bookingid, Passenger next) {
		return jdbcTemplate.update(QueryConstants.INSERT_PASSENGER.toString(),
				new Object[] { bookingid, next.getFirstname(), next.getLastname(), next.getAdult(), next.getPhoneNo(),
						next.getGender(), next.getSeatNo() },
				new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.BIT, Types.VARCHAR, Types.BIT,
						Types.VARCHAR });

	}

	@Override
	public int getSecurityCode(int cardId, String username) {
		try {
			return jdbcTemplate.queryForObject(QueryConstants.SELECT_SECURITY_CODE.toString(),
					new Object[] { cardId, username }, new int[] { Types.INTEGER, Types.VARCHAR }, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			LOG.error("BookingDao | getSecurityCode | Exception :: No security code | Card does not exist");
			return 0;
		}
	}

	@Override
	public int makeTransaction(int bookingid, int cardId, double ammountPayable) {
		return jdbcTemplate.update(QueryConstants.INSERT_TRANSACTION.toString(),
				new Object[] { bookingid, ammountPayable, cardId, "Success" },
				new int[] { Types.INTEGER, Types.DECIMAL, Types.INTEGER, Types.VARCHAR });
	}

	@Override
	public void insertFlight(Flight f) {
		jdbcTemplate.update(QueryConstants.INSERT_FLIGHT.toString(),
				new Object[] { f.getFlightNumber(), f.getOrigin().getValue(), f.getOrigin().getTerminal(),
						f.getDestination().getValue(), f.getDestination().getTerminal(), f.getAirline(),
						f.getAircraft() },
				new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR });
	}

	@Override
	public List<Booking> getActiveBookingId(String username) {
		List<Booking> bookings = new ArrayList<Booking>();
		return jdbcTemplate.query(QueryConstants.FIND_ACTIVE_BOOKING_ID.toString(), new Object[] { username },
				new ResultSetExtractor<List<Booking>>() {
					@Override
					public List<Booking> extractData(ResultSet rs) throws SQLException {
						while (rs.next()) {
							Booking b = new Booking();
							b.setBookingId(rs.getInt(1));
							bookings.add(b);
						}
						return bookings;
					}
				});
	}

	@Override
	public List<Booking> getInactiveBookingId(String username) {
		List<Booking> bookings = new ArrayList<Booking>();
		return jdbcTemplate.query(QueryConstants.FIND_INACTIVE_BOOKING_ID.toString(), new Object[] { username },
				new ResultSetExtractor<List<Booking>>() {
					@Override
					public List<Booking> extractData(ResultSet rs) throws SQLException {
						while (rs.next()) {
							Booking b = new Booking();
							b.setBookingId(rs.getInt(1));
							bookings.add(b);
						}
						return bookings;
					}
				});
	}

	@Override
	public void getBookedFlights(Booking next, String username, boolean active) {
		int a = active ? 1 : 0;
		next.setFlights(jdbcTemplate.query(QueryConstants.FIND_BOOKED_FLIGHTS.toString(),
				new Object[] { username, next.getBookingId(), a },
				new int[] { Types.VARCHAR, Types.INTEGER, Types.BIT },
				BeanPropertyRowMapper.newInstance(BookedFlight.class)));
	}

	@Override
	public void getBookedPassengers(Booking b) {
		b.setPassengers(
				jdbcTemplate.query(QueryConstants.FIND_BOOKED_PASSENGERS.toString(), new Object[] { b.getBookingId() },
						new int[] { Types.INTEGER }, BeanPropertyRowMapper.newInstance(Passenger.class)));

	}

	@Override
	public void deletePassengerForBookingId(int id) {
		jdbcTemplate.update(QueryConstants.DELETE_PASSENGER_FOR_BOOKING.toString(), new Object[] { id },
				new int[] { Types.INTEGER });
	}

	@Override
	public void inactivateBooking(int id) {
		jdbcTemplate.update(QueryConstants.INACTIVATE_BOOKING.toString(), new Object[] { id },
				new int[] { Types.INTEGER });
	}

}
