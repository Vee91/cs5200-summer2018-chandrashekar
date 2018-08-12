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
	public static final StringBuilder INSERT_PASSENGER = new StringBuilder();
	public static final StringBuilder INSERT_PERSON = new StringBuilder();
	public static final StringBuilder INSERT_USER = new StringBuilder();
	public static final StringBuilder INSERT_EMPLOYEE = new StringBuilder();
	public static final StringBuilder INSERT_FLIGHT = new StringBuilder();

	public static final StringBuilder SELECT_AIRLINE = new StringBuilder();
	public static final StringBuilder SELECT_AIRCRAFT = new StringBuilder();
	public static final StringBuilder SELECT_AIRPORT = new StringBuilder();
	public static final StringBuilder GET_SCHEDULE_ID = new StringBuilder();
	public static final StringBuilder FIND_PERSON_BY_USERNAME = new StringBuilder();
	public static final StringBuilder SELECT_CREDIT_CARD = new StringBuilder();
	public static final StringBuilder SELECT_SECURITY_CODE = new StringBuilder();
	public static final StringBuilder FIND_ACTIVE_BOOKING_ID = new StringBuilder();
	public static final StringBuilder FIND_INACTIVE_BOOKING_ID = new StringBuilder();
	public static final StringBuilder FIND_BOOKED_FLIGHTS = new StringBuilder();
	public static final StringBuilder FIND_BOOKED_PASSENGERS = new StringBuilder();

	public static final StringBuilder SCHEDULE_EXIST = new StringBuilder();
	public static final StringBuilder ITINERARY_EXIST = new StringBuilder();
	public static final StringBuilder FLIGHT_EXIST = new StringBuilder();

	public static final StringBuilder DELETE_CARD = new StringBuilder();

	public static final StringBuilder UPDATE_USERNAME = new StringBuilder();

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

		GET_SCHEDULE_ID.append(
				"SELECT id FROM cs5200_summer2018_chandrashekar.schedule where flight = ? and departure = ? and arrival = ?");

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

		INSERT_PASSENGER.append(" INSERT INTO `cs5200_summer2018_chandrashekar`.`passenger` ");
		INSERT_PASSENGER.append(" (`booking`,`firstname`,`lastname`,`adult`,`phone_no`,`gender`,`seat_number`) ");
		INSERT_PASSENGER.append(" VALUES ");
		INSERT_PASSENGER.append(" (?,?,?,?,?,?,?) ");

		INSERT_PERSON.append(" INSERT INTO `cs5200_summer2018_chandrashekar`.`person` ");
		INSERT_PERSON.append(" (`firstname`,`lastname`,`email`,`password`,`role`) ");
		INSERT_PERSON.append(" VALUES ");
		INSERT_PERSON.append(" (?,?,?,?,?) ");

		UPDATE_USERNAME.append(" update `cs5200_summer2018_chandrashekar`.`person` ");
		UPDATE_USERNAME.append(" set `username` = ? ");
		UPDATE_USERNAME.append(" where `id` = ? ");

		INSERT_USER.append(" INSERT INTO `cs5200_summer2018_chandrashekar`.`user` ");
		INSERT_USER.append(" (`id`) ");
		INSERT_USER.append(" VALUES ");
		INSERT_USER.append(" (?) ");

		INSERT_EMPLOYEE.append(" INSERT INTO `cs5200_summer2018_chandrashekar`.`employee` ");
		INSERT_EMPLOYEE.append(" (`id`) ");
		INSERT_EMPLOYEE.append(" VALUES ");
		INSERT_EMPLOYEE.append(" (?) ");

		FLIGHT_EXIST.append(
				"select count(1) from cs5200_summer2018_chandrashekar.flights where flight_number = ? and origin_airport = ? and destination_airport = ? and airline = ? and aircraft = ?  ");

		INSERT_FLIGHT.append(" INSERT INTO `cs5200_summer2018_chandrashekar`.`flights`  ");
		INSERT_FLIGHT.append(" (`flight_number`,  ");
		INSERT_FLIGHT.append(" `origin_airport`,  ");
		INSERT_FLIGHT.append(" `origin_terminal`,  ");
		INSERT_FLIGHT.append(" `destination_airport`,  ");
		INSERT_FLIGHT.append(" `destination_terminal`,  ");
		INSERT_FLIGHT.append(" `airline`,  ");
		INSERT_FLIGHT.append(" `aircraft`)  ");
		INSERT_FLIGHT.append(" VALUES  ");
		INSERT_FLIGHT.append(" (?,?,?,?,?,?,?)  ");

		FIND_ACTIVE_BOOKING_ID.append(" SELECT  ");
		FIND_ACTIVE_BOOKING_ID.append("  id ");
		FIND_ACTIVE_BOOKING_ID.append(" FROM ");
		FIND_ACTIVE_BOOKING_ID.append("  cs5200_summer2018_chandrashekar.booking_details ");
		FIND_ACTIVE_BOOKING_ID.append(" WHERE ");
		FIND_ACTIVE_BOOKING_ID.append("  user = ? AND active = 1 ");

		FIND_INACTIVE_BOOKING_ID.append(" SELECT  ");
		FIND_INACTIVE_BOOKING_ID.append("  id ");
		FIND_INACTIVE_BOOKING_ID.append(" FROM ");
		FIND_INACTIVE_BOOKING_ID.append("  cs5200_summer2018_chandrashekar.booking_details ");
		FIND_INACTIVE_BOOKING_ID.append(" WHERE ");
		FIND_INACTIVE_BOOKING_ID.append("  user = ? AND active = 0 ");

		FIND_BOOKED_FLIGHTS.append(" SELECT  ");
		FIND_BOOKED_FLIGHTS.append(" sc.flight, ");
		FIND_BOOKED_FLIGHTS.append(" sc.departure, ");
		FIND_BOOKED_FLIGHTS.append(" sc.arrival, ");
		FIND_BOOKED_FLIGHTS.append(" sc.status, ");
		FIND_BOOKED_FLIGHTS.append(" air.name AS airline, ");
		FIND_BOOKED_FLIGHTS.append(" craft.name AS aircraft, ");
		FIND_BOOKED_FLIGHTS.append(" org.name AS origin, ");
		FIND_BOOKED_FLIGHTS.append(" f.origin_terminal AS originTerminal, ");
		FIND_BOOKED_FLIGHTS.append(" dest.name AS destination, ");
		FIND_BOOKED_FLIGHTS.append(" f.destination_terminal AS destinationTerminal ");
		FIND_BOOKED_FLIGHTS.append(" FROM ");
		FIND_BOOKED_FLIGHTS.append(" cs5200_summer2018_chandrashekar.booking_details book, ");
		FIND_BOOKED_FLIGHTS.append(" cs5200_summer2018_chandrashekar.booking bd, ");
		FIND_BOOKED_FLIGHTS.append(" cs5200_summer2018_chandrashekar.schedule sc, ");
		FIND_BOOKED_FLIGHTS.append(" cs5200_summer2018_chandrashekar.flights f, ");
		FIND_BOOKED_FLIGHTS.append(" cs5200_summer2018_chandrashekar.airlines air, ");
		FIND_BOOKED_FLIGHTS.append(" cs5200_summer2018_chandrashekar.aircrafts craft, ");
		FIND_BOOKED_FLIGHTS.append(" cs5200_summer2018_chandrashekar.cities org, ");
		FIND_BOOKED_FLIGHTS.append(" cs5200_summer2018_chandrashekar.cities dest ");
		FIND_BOOKED_FLIGHTS.append(" WHERE ");
		FIND_BOOKED_FLIGHTS.append(" book.id = bd.booking ");
		FIND_BOOKED_FLIGHTS.append(" AND sc.id = bd.schedule ");
		FIND_BOOKED_FLIGHTS.append("     AND f.flight_number = sc.flight ");
		FIND_BOOKED_FLIGHTS.append("     AND air.code = f.airline ");
		FIND_BOOKED_FLIGHTS.append("     AND craft.code = f.aircraft ");
		FIND_BOOKED_FLIGHTS.append("     AND f.origin_airport = org.code ");
		FIND_BOOKED_FLIGHTS.append(" AND f.destination_airport = dest.code ");
		FIND_BOOKED_FLIGHTS.append(" AND book.user = ? ");
		FIND_BOOKED_FLIGHTS.append(" AND book.id = ? ");
		FIND_BOOKED_FLIGHTS.append(" AND book.active = ? ");
		FIND_BOOKED_FLIGHTS.append(" ORDER BY sc.departure ");

		FIND_BOOKED_PASSENGERS.append(
				" SELECT firstname, lastname, adult, gender, phone_no as phoneNo FROM cs5200_summer2018_chandrashekar.passenger where booking = ?");

	}

}
