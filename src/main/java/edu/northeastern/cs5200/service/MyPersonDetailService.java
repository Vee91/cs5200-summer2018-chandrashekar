package edu.northeastern.cs5200.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.dao.PersonDao;
import edu.northeastern.cs5200.dto.MyPersonDetails;
import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.util.ResponseResource;

@Service
public class MyPersonDetailService implements UserDetailsService {

	@Autowired
	private PersonDao personDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Person out = personDao.getPersonByUsername(username);
		return new MyPersonDetails(out);
	}

}
