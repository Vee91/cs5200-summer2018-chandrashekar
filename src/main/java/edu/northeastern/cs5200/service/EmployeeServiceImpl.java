package edu.northeastern.cs5200.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.dao.EmployeeDao;
import edu.northeastern.cs5200.dto.BookedFlight;
import edu.northeastern.cs5200.util.ResponseResource;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public ResponseResource getCrewFlights() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		ResponseResource out = new ResponseResource();
		List<BookedFlight> flights = employeeDao.getFlights(username);
		out.setCode("200");
		out.setSuccess("flights", flights);
		return out;
	}

}
