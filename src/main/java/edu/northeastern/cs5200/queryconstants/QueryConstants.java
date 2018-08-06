package edu.northeastern.cs5200.queryconstants;

public class QueryConstants {

	private QueryConstants() {

	}

	public static final StringBuilder INSERT_AIRCRAFT = new StringBuilder();
	public static final StringBuilder INSERT_AIRLINE = new StringBuilder();
	public static final StringBuilder INSERT_AIRPORT = new StringBuilder();
	public static final StringBuilder INSERT_ITINERARY = new StringBuilder();
	public static final StringBuilder INSERT_BOOKING_DETAILS = new StringBuilder();
	public static final StringBuilder CONFIRM_BOOKING = new StringBuilder();
	public static final StringBuilder ASSIGN_FLIGHT_ITINERARY = new StringBuilder();
	public static final StringBuilder INSERT_SCHEDULE = new StringBuilder();
	public static final StringBuilder INSERT_CREDIT_CARD = new StringBuilder();

	public static final StringBuilder SELECT_AIRLINE = new StringBuilder();
	public static final StringBuilder SELECT_AIRCRAFT = new StringBuilder();
	public static final StringBuilder SELECT_AIRPORT = new StringBuilder();
	public static final StringBuilder GET_SCHEDULE_ID = new StringBuilder();
	public static final StringBuilder FIND_PERSON_BY_USERNAME = new StringBuilder();
	public static final StringBuilder SELECT_CREDIT_CARD = new StringBuilder();
	public static final StringBuilder SELECT_SECURITY_CODE = new StringBuilder();

	public static final StringBuilder SCHEDULE_EXIST = new StringBuilder();
	public static final StringBuilder ITINERARY_EXIST = new StringBuilder();

	public static final StringBuilder DELETE_CARD = new StringBuilder();

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

		SCHEDULE_EXIST.append(
				"SELECT count(1) FROM cs5200_summer2018_chandrashekar.schedule where flight = ? and departure = ? and arrival = ?");

		ITINERARY_EXIST.append(
				"SELECT count(1) FROM cs5200_summer2018_chandrashekar.itinerary where origin = ? and destination = ? and duration = ?");

		GET_SCHEDULE_ID.append("SELECT id FROM cs5200_summer2018_chandrashekar.schedule where flight = ?");

		INSERT_BOOKING_DETAILS.append("INSERT INTO `cs5200_summer2018_chandrashekar`.`booking_details`");
		INSERT_BOOKING_DETAILS.append(" (`user`) ");
		INSERT_BOOKING_DETAILS.append(" VALUES ");
		INSERT_BOOKING_DETAILS.append(" (?) ");

		CONFIRM_BOOKING.append("INSERT INTO `cs5200_summer2018_chandrashekar`.`booking` ");
		CONFIRM_BOOKING.append(" (`booking`,`schedule`) ");
		CONFIRM_BOOKING.append(" VALUES ");
		CONFIRM_BOOKING.append(" (?,?) ");

		INSERT_ITINERARY.append("INSERT INTO `cs5200_summer2018_chandrashekar`.`itinerary` ");
		INSERT_ITINERARY.append(" (`origin`,`destination`,`duration`) ");
		INSERT_ITINERARY.append(" VALUES ");
		INSERT_ITINERARY.append(" (?,?,?) ");

		ASSIGN_FLIGHT_ITINERARY.append(" INSERT INTO `cs5200_summer2018_chandrashekar`.`flights_in_itinerary` ");
		ASSIGN_FLIGHT_ITINERARY.append(" (`itinerary`,`schedule`) ");
		ASSIGN_FLIGHT_ITINERARY.append(" VALUES ");
		ASSIGN_FLIGHT_ITINERARY.append(" (?,?) ");

		INSERT_SCHEDULE.append(" INSERT INTO `cs5200_summer2018_chandrashekar`.`schedule` ");
		INSERT_SCHEDULE.append(" (`flight`,`departure`,`arrival`) ");
		INSERT_SCHEDULE.append(" VALUES ");
		INSERT_SCHEDULE.append(" (?,?,?) ");

		FIND_PERSON_BY_USERNAME.append(" SELECT ");
		FIND_PERSON_BY_USERNAME.append(" `person`.`firstname`, ");
		FIND_PERSON_BY_USERNAME.append(" `person`.`lastname`, ");
		FIND_PERSON_BY_USERNAME.append(" `person`.`username`, ");
		FIND_PERSON_BY_USERNAME.append(" `person`.`password`, ");
		FIND_PERSON_BY_USERNAME.append(" `person`.`role` ");
		FIND_PERSON_BY_USERNAME
				.append(" FROM `cs5200_summer2018_chandrashekar`.`person` where `person`.`username` = ? ");

		INSERT_CREDIT_CARD.append(" INSERT INTO `cs5200_summer2018_chandrashekar`.`creditcard` ");
		INSERT_CREDIT_CARD.append(" (`user`,`card_number`,`full_name`,`exp_month`,`exp_year`,`security_code`) ");
		INSERT_CREDIT_CARD.append(" VALUES ");
		INSERT_CREDIT_CARD.append(" (?,?,?,?,?,?) ");

		SELECT_CREDIT_CARD.append(" SELECT `creditcard`.`user`, ");
		SELECT_CREDIT_CARD.append(" `creditcard`.`id`, `creditcard`.`card_number` as cardNumber, ");
		SELECT_CREDIT_CARD.append(" `creditcard`.`full_name` as fullName, ");
		SELECT_CREDIT_CARD.append(" `creditcard`.`exp_month` as expMonth, ");
		SELECT_CREDIT_CARD.append(" `creditcard`.`exp_year` as expYear, ");
		SELECT_CREDIT_CARD.append(" FROM `cs5200_summer2018_chandrashekar`.`creditcard` ");
		SELECT_CREDIT_CARD.append(" where user = ? ");

		SELECT_SECURITY_CODE.append(" SELECT `creditcard`.`security_code` ");
		SELECT_SECURITY_CODE.append(" FROM `cs5200_summer2018_chandrashekar`.`creditcard` ");
		SELECT_SECURITY_CODE.append(" where id = ? and user = ? ");

		DELETE_CARD.append(" DELETE FROM `cs5200_summer2018_chandrashekar`.`creditcard` ");
		DELETE_CARD.append(" WHERE id = ? and user = ? ");

	}

}
