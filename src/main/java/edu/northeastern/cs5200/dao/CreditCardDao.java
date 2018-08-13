package edu.northeastern.cs5200.dao;

import java.util.List;

import edu.northeastern.cs5200.dto.CreditCard;

public interface CreditCardDao {

	public int addCreditCard(CreditCard cc, String username);

	public List<CreditCard> getAllCreditCard(String username);

	public int getSecurityCode(int id, String username);

	public int deleteCard(int id, String username);

	public void updateCard(CreditCard card);

	public String getUserId(int id);

}
