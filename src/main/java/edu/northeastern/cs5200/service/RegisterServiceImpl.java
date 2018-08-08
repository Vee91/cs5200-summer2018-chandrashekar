package edu.northeastern.cs5200.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5200.dao.RegistrationDao;
import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.util.ResponseResource;

@Service
public class RegisterServiceImpl implements RegisterService {

	private static final Logger LOG = LoggerFactory.getLogger(RegisterServiceImpl.class);

	@Autowired
	private RegistrationDao registerDao;

	@Transactional
	@Override
	public ResponseResource register(Person person) {
		ResponseResource out = new ResponseResource();
		// TODO validate null parameters
		if ((person.getRole() != null) && !(person.getRole().equals("EMPLOYEE") || person.getRole().equals("USER"))) {
			out.setCode("400");
			out.setMessage("Please enter one of EMPLOYEE or USER for role");
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(person.getPassword());
			person.setPassword(hashedPassword);
			String username = registerDao.generateUserName(person);
			if (person.getRole().equals("USER")) {
				registerDao.insertUser(person.getId());
			} else if (person.getRole().equals("EMPLOYEE")) {
				registerDao.insertEmployee(person.getId());
			}
			out.setCode("200");
			out.setMessage(username);
		}
		return out;
	}

}
