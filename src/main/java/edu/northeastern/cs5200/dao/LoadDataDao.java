package edu.northeastern.cs5200.dao;

import java.sql.Types;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import edu.northeastern.cs5200.dto.Aircraft;
import edu.northeastern.cs5200.dto.Airline;
import edu.northeastern.cs5200.queryconstants.QueryConstants;

@Repository
public class LoadDataDao {

	private static final Logger LOG = LoggerFactory.getLogger(LoadDataDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public void loadAircraft() {/*
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Aircraft> aircraftList = mapper.readValue(aircrafts, new TypeReference<List<Aircraft>>() {
			});
			for (int i = 0; i < aircraftList.size(); i++) {
				Aircraft aircraft = aircraftList.get(i);
				insertAircraft(aircraft);
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	*/}

	public void loadAirline() {/*
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Airline> airlineList = mapper.readValue(airlines, new TypeReference<List<Airline>>() {
			});
			for (int i = 0; i < airlineList.size(); i++) {
				Airline airline = airlineList.get(i);
				insertAirline(airline);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	*/}

	private void insertAircraft(Aircraft aircraft) {
		Object[] inParams = { aircraft.getCode(), aircraft.getName() };
		int[] paramTypes = { Types.VARCHAR, Types.VARCHAR };
		jdbcTemplate.update(QueryConstants.INSERT_AIRCRAFT.toString(), inParams, paramTypes);
	}

	private void insertAirline(Airline airline) {
		try {
			Object[] inParams = { airline.getCode(), airline.getName() };
			int[] paramTypes = { Types.VARCHAR, Types.VARCHAR };
			jdbcTemplate.update(QueryConstants.INSERT_AIRLINE.toString(), inParams, paramTypes);
		} catch (DataAccessException ex) {
			LOG.error("Error inserting airline");
		}
	}

	public void loadAirports() {
		String uri = "http://iatacodes.org/api/v6/airports?api_key=f9e700b0-0098-49b2-b424-a6fcb0774a70";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<String>() {
				});
		// TODO do the response validation for no output or wrong output
		insertAirport(response.getBody());

	}

	private void insertAirport(String airports) {
		try {
			JSONObject json = new JSONObject(airports);
			JSONArray jsonArr = json.getJSONArray("response");
			for (int i = 0; i < jsonArr.length(); i++) {
				try {
					Object[] inParams = { jsonArr.getJSONObject(i).get("code"), jsonArr.getJSONObject(i).get("name") };
					int[] paramTypes = { Types.VARCHAR, Types.VARCHAR };
					jdbcTemplate.update(QueryConstants.INSERT_AIRPORT.toString(), inParams, paramTypes);
				} catch (Exception e) {
					LOG.error("LoadDataDao | insertAirport | Exception :: Duplicate Key | airport : "
							+ jsonArr.getJSONObject(i));
				}
			}
		} catch (JSONException e) {

		}
	}

}
