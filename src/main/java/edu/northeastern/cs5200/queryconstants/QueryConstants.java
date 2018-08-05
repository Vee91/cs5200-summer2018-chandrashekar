package edu.northeastern.cs5200.queryconstants;

public class QueryConstants {

	private QueryConstants() {

	}

	public static final StringBuilder INSERT_AIRCRAFT = new StringBuilder();
	public static final StringBuilder INSERT_AIRLINE = new StringBuilder();
	public static final StringBuilder INSERT_AIRPORT = new StringBuilder();
	public static final StringBuilder SELECT_AIRLINE = new StringBuilder();
	public static final StringBuilder SELECT_AIRCRAFT = new StringBuilder();
	public static final StringBuilder SELECT_AIRPORT = new StringBuilder();

	static {
		INSERT_AIRCRAFT.append("INSERT INTO `cs5200_summer2018_chandrashekar`.`aircrafts` ");
		INSERT_AIRCRAFT.append(" (`code`,");
		INSERT_AIRCRAFT.append(" `name`)");
		INSERT_AIRCRAFT.append(" VALUES");
		INSERT_AIRCRAFT.append(" (?,?)");

		INSERT_AIRLINE.append("INSERT INTO `cs5200_summer2018_chandrashekar`.`airlines` ");
		INSERT_AIRLINE.append(" (`code`,");
		INSERT_AIRLINE.append(" `name`)");
		INSERT_AIRLINE.append(" VALUES");
		INSERT_AIRLINE.append(" (?,?)");
		
		INSERT_AIRPORT.append("INSERT INTO `cs5200_summer2018_chandrashekar`.`cities` ");
		INSERT_AIRPORT.append(" (`code`,");
		INSERT_AIRPORT.append(" `name`)");
		INSERT_AIRPORT.append(" VALUES");
		INSERT_AIRPORT.append(" (?,?)");

		SELECT_AIRLINE.append("SELECT name FROM cs5200_summer2018_chandrashekar.airlines where code = ?");

		SELECT_AIRCRAFT.append("SELECT name FROM cs5200_summer2018_chandrashekar.aircrafts where code = ?");
		
		SELECT_AIRPORT.append("SELECT name FROM cs5200_summer2018_chandrashekar.cities where code = ?");
	}

}
