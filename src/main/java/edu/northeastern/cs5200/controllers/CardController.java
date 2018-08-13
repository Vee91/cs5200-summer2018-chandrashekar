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
	public ResponseResource addCreditCard() {
		// TODO
		CreditCard c = new CreditCard();
		return creditService.addCreditCard(c);
	}

	@RequestMapping(value = { "/card" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseResource getAllCreditCard() {
		return creditService.getAllCreditCard();
	}

	@RequestMapping(value = { "/card/delete" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseResource deleteCard(@RequestBody int id) {
		return creditService.deleteCard(id);
	}
	
	@RequestMapping(value = { "/card/update" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseResource updateCard(@RequestBody CreditCard card) {
		return creditService.updateCard(card);
	}
}
