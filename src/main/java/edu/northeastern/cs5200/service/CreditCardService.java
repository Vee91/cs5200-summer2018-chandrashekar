package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.util.ResponseResource;

public interface CreditCardService {

	ResponseResource addCreditCard(CreditCard c);

	ResponseResource getAllCreditCard();

	ResponseResource deleteCard(int id, int securityCode);

}
