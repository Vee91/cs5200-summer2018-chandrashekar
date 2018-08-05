package edu.northeastern.cs5200.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.queryconstants.QueryConstants;

@Repository
public class FlightDaoImpl implements FlightDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(FlightDaoImpl.class);

	@Override
	public String getAirline(String airline) {
		try {
			return jdbcTemplate.queryForObject(QueryConstants.SELECT_AIRLINE.toString(), new Object[] { airline },
					String.class);
		} catch (EmptyResultDataAccessException e) {
			LOG.error("FlightDaoImpl | getAirline | Exception :: EmptyResultDataAccessException | airline : " + airline
					+ " ", e);
			return "";
		}
	}

	@Override
	public String getAircraft(String aircraft) {
		try {
			return jdbcTemplate.queryForObject(QueryConstants.SELECT_AIRCRAFT.toString(), new Object[] { aircraft },
					String.class);
		} catch (EmptyResultDataAccessException e) {
			LOG.error("FlightDaoImpl | getAircraft | Exception :: EmptyResultDataAccessException | aircraft : "
					+ aircraft + " ", e);
			return "";
		}
	}

	@Override
	public String getAirport(String code) {
		try {
			return jdbcTemplate.queryForObject(QueryConstants.SELECT_AIRPORT.toString(), new Object[] { code },
					String.class);
		} catch (EmptyResultDataAccessException e) {
			LOG.error("FlightDaoImpl | getAirport | Exception :: EmptyResultDataAccessException | airport : " + code
					+ " ", e);
			return "";
		}
	}

}
