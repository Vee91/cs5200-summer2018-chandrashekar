package edu.northeastern.cs5200.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.dto.CreditCard;
import edu.northeastern.cs5200.service.CreditCardService;
import edu.northeastern.cs5200.util.ResponseResource;

@RestController
@RequestMapping("/api")
public class CardController {

	// TODO convert to post
	private static final Logger LOG = LoggerFactory.getLogger(CardController.class);

	@Autowired
	private CreditCardService creditService;

	// TODO authentication
	@RequestMapping(value = { "/card/add" }, method = RequestMethod.GET)
	@ResponseBody
	ResponseResource addCreditCard() {
		// TODO
		CreditCard c = new CreditCard();
		return creditService.addCreditCard(c);
	}

	// TODO authentication
	@RequestMapping(value = { "/card" }, method = RequestMethod.GET)
	@ResponseBody
	ResponseResource getAllCreditCard() {
		return creditService.getAllCreditCard();
	}

	// TODO authentication
	@RequestMapping(value = { "/card/delete" }, method = RequestMethod.GET)
	@ResponseBody
	ResponseResource deleteCard() {
		// TODO
		int id = 0;
		int securityCode = 0;
		return creditService.deleteCard(id, securityCode);
	}
}
