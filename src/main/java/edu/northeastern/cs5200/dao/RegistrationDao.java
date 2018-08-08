package edu.northeastern.cs5200.dao;

import edu.northeastern.cs5200.dto.Person;

public interface RegistrationDao {

	public String generateUserName(Person person);

	public void insertUser(int id);

	public void insertEmployee(int id);

}
