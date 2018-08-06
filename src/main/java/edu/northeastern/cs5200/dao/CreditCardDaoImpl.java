package edu.northeastern.cs5200.dao;

import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
		return jdbcTemplate.update(QueryConstants.INSERT_CREDIT_CARD.toString(),
				new Object[] { username, cc.getCardNumber(), cc.getFullName(), cc.getExpMonth(), cc.getExpYear(),
						cc.getSecurityCode() },
				new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER });
	}

	@Override
	public List<CreditCard> getAllCreditCard(String username) {
		return jdbcTemplate.queryForList(QueryConstants.SELECT_CREDIT_CARD.toString(), new Object[] { username },
				new int[] { Types.VARCHAR }, CreditCard.class);
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

}
