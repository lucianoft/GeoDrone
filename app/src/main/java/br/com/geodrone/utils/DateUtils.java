package br.com.geodrone.utils;

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
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			date = cal.getTime();
		}
		return date;
	}

	public boolean equals(Date date, Date anotherDate){
		if (date == null){
			return anotherDate == null;
		}
		return date.compareTo(anotherDate) == 0;
	}

}