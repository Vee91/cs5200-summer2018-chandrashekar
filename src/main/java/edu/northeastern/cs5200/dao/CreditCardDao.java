package edu.northeastern.cs5200.dao;

import java.util.List;

import edu.northeastern.cs5200.dto.CreditCard;

public interface CreditCardDao {

	int addCreditCard(CreditCard cc, String username);

	List<CreditCard> getAllCreditCard(String username);

	int getSecurityCode(int id, String username);

	int deleteCard(int id, String username);

}
