package edu.northeastern.cs5200.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.dto.Airport;
import edu.northeastern.cs5200.dto.FlightSearch;
import edu.northeastern.cs5200.service.SearchService;
import edu.northeastern.cs5200.util.DateUtil;
import edu.northeastern.cs5200.util.ResponseResource;

@RestController
@RequestMapping("/api")
public class SearchController {

	@Autowired
	private SearchService searchService;
	// TODO change to post
	private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

	@RequestMapping(value = { "/autocomplete/{value}" }, method = RequestMethod.GET)
	@ResponseBody
	ResponseResource autoComplete(@PathVariable(value = "value") String value) {
		return searchService.autoComplete(value);
	}

	@RequestMapping(value = { "/searchFlight" }, method = RequestMethod.GET)
	@ResponseBody
	ResponseResource searchFlight(/* @RequestBody FlightSearch searchQuery */) {
		FlightSearch query = new FlightSearch();
		query.setOrigin("IST");
		query.setDestination("BOS");
		query.setDepartureDate("2018-10-15");
		query.setReturnFlight(true);
		query.setReturnDate("2018-10-19");
		
		return searchService.searchFlight(query);

	}
}
