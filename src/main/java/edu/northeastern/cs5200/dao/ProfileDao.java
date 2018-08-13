package edu.northeastern.cs5200.dao;

import edu.northeastern.cs5200.dto.Person;

public interface ProfileDao {
	
	public Person getPersonProfile(String username);

	public String getPassword(String username);

	public void updateProfile(Person person, String username);

}
