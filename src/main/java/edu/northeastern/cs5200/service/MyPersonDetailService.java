package edu.northeastern.cs5200.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.dao.PersonDao;
import edu.northeastern.cs5200.dto.MyPersonDetails;
import edu.northeastern.cs5200.dto.Person;

@Service
public class MyPersonDetailService implements UserDetailsService {

	@Autowired
	private PersonDao personDao;

	// TODO if storing user password after encryption, no need to encrypt it here
	@Override
	public UserDetails loadUserByUsername(String username) {
		Person out = personDao.getPersonByUsername(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(out.getPassword());

		out.setPassword(hashedPassword);

		return new MyPersonDetails(out);
	}
}
