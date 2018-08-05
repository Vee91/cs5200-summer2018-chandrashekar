package edu.northeastern.cs5200.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.dto.MyPersonDetails;
import edu.northeastern.cs5200.dto.Person;

@Service
public class MyPersonDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Person out = new Person();
		out.setFirstname("Vikas");
		out.setLastname("C");
		out.setUsername("vikas91");
		String pass = "qwerty12345";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(pass);

		out.setPassword(hashedPassword);
		out.setRole("ADMIN");
		return new MyPersonDetails(out);
	}
}
