package edu.northeastern.cs5200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.queryconstants.QueryConstants;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String generateUserName(Person person) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(QueryConstants.INSERT_PERSON.toString(),
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, person.getFirstname());
				ps.setString(2, person.getLastname());
				ps.setString(3, person.getEmail());
				ps.setString(4, person.getPassword());
				ps.setString(5, person.getRole());
				return ps;
			}
		}, keyHolder);
		String username = person.getLastname().substring(0,
				person.getLastname().length() < 5 ? person.getLastname().length() : 5)
				+ person.getFirstname().substring(0, 1) + keyHolder.getKey().intValue();
		person.setId(keyHolder.getKey().intValue());
		updateUserName(keyHolder.getKey().intValue(), username);
		return username;

	}

	private void updateUserName(int intValue, String username) {
		jdbcTemplate.update(QueryConstants.UPDATE_USERNAME.toString(), new Object[] { username, intValue },
				new int[] { Types.VARCHAR, Types.INTEGER });

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

}
