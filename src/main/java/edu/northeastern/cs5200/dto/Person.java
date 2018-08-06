package edu.northeastern.cs5200.dto;

public class Person {

	private int id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String role;
	private String phone;
	private String email;

	public Person() {

	}

	public Person(Person users) {
		this.username = users.getUsername();
		this.password = users.getPassword();
		this.firstname = users.getFirstname();
		this.lastname = users.getLastname();
		this.role = users.getRole();
		this.id = users.getId();
		this.phone = users.getPhone();
		this.email = users.getEmail();
	}

	
	public Person(int id, String firstname, String lastname, String username, String password, String role,
			String phone, String email) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.role = role;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
