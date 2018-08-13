package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.util.ResponseResource;

public interface CreditCardService {

	public ResponseResource addCreditCard(CreditCard c);

	public ResponseResource getAllCreditCard();

	public ResponseResource deleteCard(int id);

	public ResponseResource updateCard(CreditCard card);

}
