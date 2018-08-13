package edu.northeastern.cs5200.service;

import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.util.ResponseResource;

public interface ProfileService {

	public ResponseResource getProfile();

	public ResponseResource updateProfile(Person person);

}
