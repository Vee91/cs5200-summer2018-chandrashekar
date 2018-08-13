package edu.northeastern.cs5200.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.queryconstants.QueryConstants;

@Repository
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private JdbcTemplate jdbctemplate;

	@Override
	public Person getPersonByUsername(String username) {
		return jdbctemplate.queryForObject(QueryConstants.FIND_PERSON_BY_USERNAME.toString(), new Object[] { username },
				BeanPropertyRowMapper.newInstance(Person.class));
	}
	
}
