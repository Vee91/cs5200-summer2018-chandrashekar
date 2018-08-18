package edu.northeastern.cs5200.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.dto.BookedFlight;
import edu.northeastern.cs5200.queryconstants.QueryConstants;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<BookedFlight> getFlights(String username) {
		return jdbcTemplate.query(QueryConstants.GET_CREW_ASSIGNMENT.toString(), new Object[] { username },
				new int[] { Types.VARCHAR }, BeanPropertyRowMapper.newInstance(BookedFlight.class));
	}

}
