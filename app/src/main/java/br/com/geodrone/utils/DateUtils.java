package br.com.geodrone.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/**
	 * Retorna a data atual
	 *
	 * @return java.time.LocalDate
	 */
	public Date now() {
		return new Date();
	}

	/**
	 * Retorna a data atual truncada
	 *
	 * @return java.time.LocalDate
	 */
	public Date nowTrunc() {
		return trunc(now());
	}

	/**
	 * Trunca a data informada
	 *
	 * @param date - java.time.LocalDate
	 * @return Date - java.time.LocalDate
	 */
	public Date trunc(Date date) {
		if (date != null) {
		}
		return date;
	}
	

}