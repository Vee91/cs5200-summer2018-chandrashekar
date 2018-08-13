package edu.northeastern.cs5200.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.dao.ProfileDao;
import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.util.ResponseResource;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileDao profileDao;

	@Override
	public ResponseResource getProfile() {
		ResponseResource out = new ResponseResource();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		out.setCode("200");
		out.setSuccess("profile", profileDao.getPersonProfile(username));
		return out;
	}

	@Override
	public ResponseResource updateProfile(Person person) {
		ResponseResource out = new ResponseResource();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String encPass = profileDao.getPassword(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(passwordEncoder.matches(person.getPassword(), encPass)) {
			profileDao.updateProfile(person, username);
			out.setCode("200");
			out.setMessage("Profile updated successfully");
		}
		else {
			out.setCode("400");
			out.setMessage("Your password does not match your current password");
		}
		return out;
	}

}
