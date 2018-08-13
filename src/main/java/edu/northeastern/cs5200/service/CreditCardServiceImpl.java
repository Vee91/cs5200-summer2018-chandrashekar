package edu.northeastern.cs5200.service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.dao.CreditCardDao;
import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.util.ResponseResource;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	private static final Logger LOG = LoggerFactory.getLogger(CreditCardServiceImpl.class);

	@Autowired
	private CreditCardDao creditDao;

	@Override
	public ResponseResource addCreditCard(CreditCard cc) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		int db = creditDao.addCreditCard(cc, username);
		ResponseResource out = new ResponseResource();
		if (db > 0) {
			out.setCode("200");
			out.setMessage("Credit Card Added Successfully");
			return out;
		} else {
			out.setCode("400");
			out.setMessage("There was some problem with adding your credit card. Please try again");
			return out;
		}
	}

	@Override
	public ResponseResource getAllCreditCard() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<CreditCard> credits = creditDao.getAllCreditCard(username);
		maskNumber(credits);
		ResponseResource out = new ResponseResource();
		out.setCode("200");
		out.setSuccess("cards", credits);
		return out;
	}

	private void maskNumber(List<CreditCard> credits) {
		Iterator<CreditCard> itr = credits.iterator();
		while (itr.hasNext()) {
			StringBuilder sb = new StringBuilder();
			CreditCard c = itr.next();
			String unmasked = c.getCardNumber();
			for (int i = 0; i < unmasked.length() - 4; i++) {
				if (i == 4 || i == 9 || i == 14)
					sb.append("-");
				else
					sb.append("*");
			}
			c.setCardNumber(sb.toString() + unmasked.substring(15));
		}

	}

	@Override
	public ResponseResource deleteCard(int id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String userId = creditDao.getUserId(id);
		ResponseResource out = new ResponseResource();
		if (username.equalsIgnoreCase(userId)) {
			int success = creditDao.deleteCard(id, username);
			if (success > 0) {
				out.setCode("200");
				out.setMessage("Card Deleted");
			} else {
				out.setCode("400");
				out.setMessage("Something went wrong. Please try again");
			}
		} else {
			out.setCode("400");
			out.setMessage("Only owners can delete the card");
		}

		return out;
	}

	@Override
	public ResponseResource updateCard(CreditCard card) {
		ResponseResource out = new ResponseResource();
		String userId = creditDao.getUserId(card.getId());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		if (username.equalsIgnoreCase(userId)) {
			creditDao.updateCard(card);
			out.setCode("200");
		} else {
			out.setCode("400");
			out.setMessage("Only card owners can update the card");
		}
		return out;
	}

}
