package edu.northeastern.cs5200.dao;

import edu.northeastern.cs5200.dto.Person;

public interface PersonDao {

	public Person getPersonByUsername(String username);

}
