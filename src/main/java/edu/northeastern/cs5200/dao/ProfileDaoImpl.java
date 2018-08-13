package edu.northeastern.cs5200.dao;

import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.queryconstants.QueryConstants;

@Repository
public class ProfileDaoImpl implements ProfileDao {

	@Autowired
	private JdbcTemplate jdbctemplate;

	@Override
	public Person getPersonProfile(String username) {
		return jdbctemplate.queryForObject(QueryConstants.FIND_PERSON_PROFILE.toString(), new Object[] { username },
				BeanPropertyRowMapper.newInstance(Person.class));
	}

	@Override
	public String getPassword(String username) {
		return jdbctemplate.queryForObject(QueryConstants.SELECT_PASSWORD.toString(), new Object[] { username },
				String.class);
	}

	@Override
	public void updateProfile(Person person, String username) {
		jdbctemplate.update(QueryConstants.UPDATE_PROFILE.toString(),
				new Object[] { person.getFirstname(), person.getLastname(), person.getPhone(), person.getEmail(),
						person.getUsername(), username },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR });
	}
}
