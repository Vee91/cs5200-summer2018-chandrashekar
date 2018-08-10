package edu.northeastern.cs5200.dao;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import edu.northeastern.cs5200.dto.Airport;
import edu.northeastern.cs5200.dto.FlightSearch;
import edu.northeastern.cs5200.util.DateUtil;
import edu.northeastern.cs5200.util.TravelConstants;

@Repository
public class ThirdPartyImpl implements ThirdParty {

	@Override
	public List<Airport> autoComplete(String value) {
		final String uri = TravelConstants.RESOURCE_URL + "airports/autocomplete?apikey=" + TravelConstants.API_KEY
				+ "&term=" + value;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Airport>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Airport>>() {
				});
		// TODO do the response validation for no output or wrong output
		return response.getBody();
	}

	@Override
	public String searchFlight(FlightSearch query) {
		String uri = "https://api.sandbox.amadeus.com/v1.2/flights/low-fare-search?origin=" + query.getOrigin()
				+ "&destination=" + query.getDestination() + "&departure_date="
				+ DateUtil.getDateAsString(DateUtil.getDateFromString(query.getDepartureDate(), "yyyy-mm-dd"), "yyyy-mm-dd");
		if (query.getReturnDate() != null)
			uri = uri + "&return_date=" + query.getReturnDate();
		uri = uri + "&number_of_results=6&nonstop=" + query.isNonstop() + "&apikey=" + TravelConstants.API_KEY;

		RestTemplate restTemplate = new RestTemplate();
		try {
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<String>() {
					});
			// TODO do the response validation for no output or wrong output
			return response.getBody();
		} catch (HttpClientErrorException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public String getAirport(String code) {
		String uri = "http://iatacodes.org/api/v6/airports?api_key=" + TravelConstants.IATA_API_KEY + "&code=" + code;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<String>() {
				});
		// TODO do the response validation for no output or wrong output
		return response.getBody();
	}

}
