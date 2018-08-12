package edu.northeastern.cs5200.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

	private DateUtil() {

	}

	public static Date getDateFromString(String source, String format) {
		DateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			LOG.error("DateUtils | getDateFromString | source:" + source + " | format:" + format + " | ParseException:",
					e);
		}
		return null;
	}
	
	public static String getDateAsString(String dateString, String oldFormat, String newFormat) {
		Date date = getDateFromString(dateString, oldFormat);
		return getDateAsString(date, newFormat);
	} 
	
	public static String getDateAsString(Date date,String format){
		if(date!=null) {
			LOG.info("DateUtils | getDateAsString | source:"+date+" | format:"+format);
			DateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}
		LOG.info("DateUtils | getDateAsString | source:= | format:"+format+" | DATE_IS_NULL");
		return null;
	}

	public static java.sql.Timestamp dateToSQLDate(Date date) {
		java.sql.Timestamp sqlDate = null;
		if (date != null) {
			sqlDate = new java.sql.Timestamp(date.getTime());
		}
		return sqlDate;
	}

	public static Date getDateFromISO(String source) {
		return dateFromInstant(Instant.parse(source));
	}

	private static Date dateFromInstant(Instant parse) {
		return Date.from(parse);
	}

}
