package edu.northeastern.cs5200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.queryconstants.QueryConstants;

@Repository
public class CreditCardDaoImpl implements CreditCardDao {

	private static final Logger LOG = LoggerFactory.getLogger(CreditCardDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int addCreditCard(CreditCard cc, String username) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(QueryConstants.INSERT_CREDIT_CARD.toString(),
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, username);
				ps.setString(2, cc.getCardNumber());
				ps.setString(3, cc.getFullName());
				ps.setInt(4, cc.getExpMonth());
				ps.setInt(5, cc.getExpYear());
				ps.setInt(6, cc.getSecurityCode());
				return ps;
			}
		}, keyHolder);
		cc.setId(keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public List<CreditCard> getAllCreditCard(String username) {
		return jdbcTemplate.query(QueryConstants.SELECT_CREDIT_CARD.toString(), new Object[] { username },
				new int[] { Types.VARCHAR }, BeanPropertyRowMapper.newInstance(CreditCard.class));
	}

	@Override
	public int getSecurityCode(int id, String username) {
		try {
			return jdbcTemplate.queryForObject(QueryConstants.SELECT_SECURITY_CODE.toString(),
					new Object[] { id, username }, new int[] { Types.INTEGER, Types.VARCHAR }, Integer.class);
		} catch (EmptyResultDataAccessException e) {
			LOG.error("CreditCardDao | getSecurityCode | Exception :: No security code | Card does not exist");
			return 0;
		}
	}

	@Override
	public int deleteCard(int id, String username) {
		return jdbcTemplate.update(QueryConstants.DELETE_CARD.toString(), new Object[] { id, username },
				new int[] { Types.INTEGER, Types.VARCHAR });
	}

	@Override
	public void updateCard(CreditCard card) {
		jdbcTemplate.update(QueryConstants.UPDATE_CARD.toString(),
				new Object[] { card.getCardNumber(), card.getFullName(), card.getExpMonth(), card.getExpYear(),
						card.getSecurityCode(), card.getId() },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER });
	}

	@Override
	public String getUserId(int id) {
		return jdbcTemplate.queryForObject(QueryConstants.CARD_USER_ID.toString(), new Object[] { id }, String.class);
	}

}
