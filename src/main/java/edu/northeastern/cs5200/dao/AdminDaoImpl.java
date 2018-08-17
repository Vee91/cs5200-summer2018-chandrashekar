package edu.northeastern.cs5200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.dto.AdminFlight;
import edu.northeastern.cs5200.dto.Aircraft;
import edu.northeastern.cs5200.dto.Airline;
import edu.northeastern.cs5200.dto.Airport;
import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.dto.Flight;
import edu.northeastern.cs5200.dto.Passenger;
import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.queryconstants.QueryConstants;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Person> getUsers() {
		return jdbcTemplate.query(QueryConstants.GET_ALL_USERS.toString(), new Object[] {}, new int[] {},
				BeanPropertyRowMapper.newInstance(Person.class));
	}

	@Override
	public void insertPerson(Person person) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						QueryConstants.INSERT_PERSON_WITH_USERNAME.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, person.getFirstname());
				ps.setString(2, person.getLastname());
				ps.setInt(3, person.getPhone());
				ps.setString(4, person.getEmail());
				ps.setString(5, person.getUsername());
				ps.setString(6, person.getPassword());
				ps.setString(7, person.getRole());
				return ps;
			}
		}, keyHolder);

		person.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void insertUser(int id) {
		jdbcTemplate.update(QueryConstants.INSERT_USER.toString(), new Object[] { id }, new int[] { Types.INTEGER });
	}

	@Override
	public void insertEmployee(int id) {
		jdbcTemplate.update(QueryConstants.INSERT_EMPLOYEE.toString(), new Object[] { id },
				new int[] { Types.INTEGER });
	}

	@Override
	public void updateProfile(Person person) {
		jdbcTemplate.update(QueryConstants.UPDATE_PROFILE_BY_ID.toString(),
				new Object[] { person.getFirstname(), person.getLastname(), person.getPhone(), person.getEmail(),
						person.getUsername(), person.getId() },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER });
	}

	@Override
	public List<AdminFlight> getFlights() {
		List<AdminFlight> flights = new ArrayList<>();
		return jdbcTemplate.query(QueryConstants.SELECT_ADMIN_FLIGHTS.toString(), new Object[] {},
				new ResultSetExtractor<List<AdminFlight>>() {
					@Override
					public List<AdminFlight> extractData(ResultSet rs) throws SQLException {
						while (rs.next()) {
							AdminFlight flight = new AdminFlight();
							Airport origin = new Airport();
							Airport destination = new Airport();
							Airline airline = new Airline();
							Aircraft aircraft = new Aircraft();
							flight.setFlightNumber(rs.getInt(1));
							flight.setOriginTerminal(rs.getString(4));
							flight.setDestinationTerminal(rs.getString(7));
							origin.setValue(rs.getString(3));
							origin.setLabel(rs.getString(2));
							flight.setOrigin(origin);
							destination.setValue(rs.getString(5));
							destination.setLabel(rs.getString(6));
							flight.setDestination(destination);
							airline.setCode(rs.getString(10));
							airline.setName(rs.getString(11));
							flight.setAirline(airline);
							aircraft.setCode(rs.getString(8));
							aircraft.setName(rs.getString(9));
							flight.setAircraft(aircraft);
							flights.add(flight);
						}
						return flights;
					}
				});
	}

	@Override
	public List<Airport> autoComplete(String value) {
		return jdbcTemplate.query(QueryConstants.GET_AIRPORT_AUTOCOMPLETE.toString(),
				new Object[] { "%" + value + "%" }, new int[] { Types.VARCHAR },
				BeanPropertyRowMapper.newInstance(Airport.class));
	}

	@Override
	public List<Airline> autoCompleteAirline(String value) {
		return jdbcTemplate.query(QueryConstants.GET_AIRLINE_AUTOCOMPLETE.toString(),
				new Object[] { "%" + value + "%" }, new int[] { Types.VARCHAR },
				BeanPropertyRowMapper.newInstance(Airline.class));
	}

	@Override
	public List<Aircraft> autoCompleteAircraft(String value) {
		return jdbcTemplate.query(QueryConstants.GET_AIRCRAFT_AUTOCOMPLETE.toString(),
				new Object[] { "%" + value + "%" }, new int[] { Types.VARCHAR },
				BeanPropertyRowMapper.newInstance(Aircraft.class));
	}

	@Override
	public void insertFlight(AdminFlight flight) {
		jdbcTemplate.update(QueryConstants.INSERT_FLIGHT.toString(),
				new Object[] { flight.getFlightNumber(), flight.getOrigin().getValue(), flight.getOriginTerminal(),
						flight.getDestination().getValue(), flight.getDestinationTerminal(),
						flight.getAirline().getCode(), flight.getAircraft().getCode() },
				new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR });
	}

	@Override
	public void updateFlight(AdminFlight flight) {
		jdbcTemplate.update(QueryConstants.UPDATE_FLIGHT.toString(),
				new Object[] { flight.getOrigin().getValue(), flight.getOriginTerminal(),
						flight.getDestination().getValue(), flight.getDestinationTerminal(),
						flight.getAirline().getCode(), flight.getAircraft().getCode(), flight.getFlightNumber() },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
						Types.INTEGER });
	}

	@Override
	public void insertCard(CreditCard cc) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(QueryConstants.INSERT_CREDIT_CARD.toString(),
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, cc.getUser());
				ps.setString(2, cc.getCardNumber());
				ps.setString(3, cc.getFullName());
				ps.setInt(4, cc.getExpMonth());
				ps.setInt(5, cc.getExpYear());
				ps.setInt(6, cc.getSecurityCode());
				return ps;
			}
		}, keyHolder);
		cc.setId(keyHolder.getKey().intValue());
	}

	@Override
	public List<Integer> getBookings() {
		return jdbcTemplate.queryForList(QueryConstants.GET_ALL_BOOKING_ID.toString(), new Object[] {}, Integer.class);
	}

	@Override
	public void insertPassengerToDB(int bookingid, Passenger next) {
		jdbcTemplate.update(QueryConstants.INSERT_PASSENGER.toString(),
				new Object[] { bookingid, next.getFirstname(), next.getLastname(), next.getAdult(), next.getPhoneNo(),
						next.getGender(), next.getSeatNo() },
				new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.BIT, Types.VARCHAR, Types.BIT,
						Types.VARCHAR });
	}

	@Override
	public List<Flight> getSchedules() {
		return jdbcTemplate.query(QueryConstants.GET_ALL_SCHEDULES.toString(), new Object[] {}, new int[] {},
				BeanPropertyRowMapper.newInstance(Flight.class));
	}

	@Override
	public List<String> getEmployees() {
		return jdbcTemplate.queryForList(QueryConstants.GET_ALL_EMPLOYEES.toString(), String.class);
	}

	@Override
	public void assignCrew(int scheduleId, String employee) {
		jdbcTemplate.update(QueryConstants.ASSIGN_CREW.toString(), new Object[] { scheduleId, employee },
				new int[] { Types.INTEGER, Types.VARCHAR });
	}

}
