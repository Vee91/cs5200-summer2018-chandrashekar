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
			query.setReturnFlight(query.getReturnDate() == null ? false : true);
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
			f.setOperatingAirlineName(getAirline(f.getAirline(), infoMap));
			f.setAircraftName(getAircraft(f.getAircraft(), infoMap));
		}

	}

	private String getAircraft(String aircraft, Map<String, Object> infoMap) {
		if (!infoMap.containsKey(aircraft)) {
			String name = flightDao.getAircraft(aircraft);
			infoMap.put(aircraft, name);
			return name;
		}
		else {
			return infoMap.get(aircraft).toString();
		}
	}

	private String getAirline(String airline, Map<String, Object> infoMap) {
		if (!infoMap.containsKey(airline)) {
			String name = flightDao.getAirline(airline);
			infoMap.put(airline, name);
			return name;
		}
		else {
			return infoMap.get(airline).toString();
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
			result.setPrice(Float.parseFloat(fare.getString("total_price")));
			result.setTax(Float.parseFloat(fare.getJSONObject("price_per_adult").getString("tax")));
			output.add(result);
		}

		return output;
	}

	private void getAirport(Airport code, Map<String, String> cache) throws JSONException {
		if (cache.containsKey(code.getValue())) {
			code.setLabel(cache.get(code.getValue()));
			return;
		}

		if (code.getValue() != null) {
			code.setLabel(flightDao.getAirport(code.getValue()));
		}
	}

	@Override
	public ResponseResource init() {
		ResponseResource out = new ResponseResource();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		if (username.equals("anonymousUser")) {
			out.setLoggedin(false);
		} else {
			out.setLoggedin(true);
		}
		return out;
	}

}
