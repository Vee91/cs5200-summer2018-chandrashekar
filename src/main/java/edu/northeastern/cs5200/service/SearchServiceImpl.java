package edu.northeastern.cs5200.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.northeastern.cs5200.dao.FlightDao;
import edu.northeastern.cs5200.dao.ThirdParty;
import edu.northeastern.cs5200.dto.Airport;
import edu.northeastern.cs5200.dto.Flight;
import edu.northeastern.cs5200.dto.FlightSearch;
import edu.northeastern.cs5200.dto.FlightSearchResult;
import edu.northeastern.cs5200.dto.Itinerary;
import edu.northeastern.cs5200.util.ResponseResource;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private ThirdParty thirdParty;

	@Autowired
	private FlightDao flightDao;

	@Override
	public ResponseResource autoComplete(String value) {
		// TODO do value sanitize and validation
		ResponseResource resp = new ResponseResource();
		resp.setCode("200");
		resp.setSuccess("airports", thirdParty.autoComplete(value));
		return resp;
	}

	@Override
	public ResponseResource searchFlight(FlightSearch query) {
		// TODO sanitize and validate input
		try {
			String jsonResult = thirdParty.searchFlight(query);
			JSONObject json = new JSONObject(jsonResult);
			List<FlightSearchResult> results = formatSearchResult(json, query.isReturnFlight(), query.getOrigin(),
					query.getDestination());
			Iterator<FlightSearchResult> itr = results.iterator();
			Map<String, String> cache = new HashMap<>();
			Map<String, Object> infoMap = new HashMap<>();
			ResponseResource out = new ResponseResource();
			out.setCode("200");
			while (itr.hasNext()) {
				FlightSearchResult fsr = itr.next();
				populateAdditionalData(fsr.getOutbound().getFlights(), cache, infoMap);
				if (query.isReturnFlight())
					populateAdditionalData(fsr.getInbound().getFlights(), cache, infoMap);
			}
			out.setSuccess("results", results);
			out.setInfoMap(infoMap);
			return out;
		} catch (JSONException e) {
			// TODO return error message
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void populateAdditionalData(List<Flight> flights, Map<String, String> cache, Map<String, Object> infoMap)
			throws JSONException {
		Iterator<Flight> itr = flights.iterator();
		while (itr.hasNext()) {
			Flight f = itr.next();
			getAirport(f.getOrigin(), cache);
			getAirport(f.getDestination(), cache);
			getAirline(f.getAirline(), infoMap);
			getAircraft(f.getAircraft(), infoMap);
		}

	}

	private void getAircraft(String aircraft, Map<String, Object> infoMap) {
		if (infoMap.containsKey(aircraft)) {
			return;
		} else {
			infoMap.put(aircraft, flightDao.getAircraft(aircraft));
		}
	}

	private void getAirline(String airline, Map<String, Object> infoMap) {
		if (infoMap.containsKey(airline)) {
			return;
		} else {
			infoMap.put(airline, flightDao.getAirline(airline));
		}

	}

	private List<FlightSearchResult> formatSearchResult(JSONObject json, boolean returnFlight, String origin,
			String destination) throws JSONException, IOException {
		List<FlightSearchResult> output = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		
		JSONArray resultArr = json.getJSONArray("results");
		for (int i = 0; i < resultArr.length(); i++) {
			JSONArray itineraries = resultArr.getJSONObject(i).getJSONArray("itineraries");
			JSONObject outbound = itineraries.getJSONObject(0).getJSONObject("outbound");
			Itinerary out = mapper.readValue(outbound.toString(), Itinerary.class);
			out.setOrigin(origin);
			out.setDestination(destination);
			FlightSearchResult result = new FlightSearchResult();
			result.setOutbound(out);
			if (returnFlight) {
				JSONObject inbound = itineraries.getJSONObject(0).getJSONObject("inbound");
				Itinerary in = mapper.readValue(inbound.toString(), Itinerary.class);
				in.setOrigin(destination);
				in.setDestination(origin);
				result.setInbound(in);
			}
			JSONObject fare = resultArr.getJSONObject(i).getJSONObject("fare");
			result.setPrice(fare.getString("total_price"));
			result.setTax(fare.getJSONObject("price_per_adult").getString("tax"));
			output.add(result);
		}

		return output;
	}

	private void getAirport(Airport code, Map<String, String> cache) throws JSONException {
		if (cache.containsKey(code.getCode())) {
			code.setLabel(cache.get(code.getCode()));
			return;
		}

		if (code.getCode() != null) {
			// TODO remove code
			/*
			 * JSONObject airportObj = new
			 * JSONObject(thirdParty.getAirport(code.getCode())); JSONArray arr =
			 * airportObj.getJSONArray("response"); if (arr.length() > 0) { String name =
			 * arr.getJSONObject(0).getString("name"); code.setLabel(name);
			 * cache.put(code.getCode(), name); }
			 */
			code.setLabel(flightDao.getAirport(code.getCode()));
		}
	}

	@Override
	public ResponseResource init() {
		ResponseResource out = new ResponseResource();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		if(username.equals("anonymousUser")) {
			out.setLoggedin(false);
		} else {
			out.setLoggedin(true);
		}
		return out;
	}

}
