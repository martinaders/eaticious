package org.eaticious.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a start or an ending date of a season
 * Much more convenient than util.Date because not needing a specific Time, comparing better (before, after)
 * 
 * month: 1 is January, 12 is December
 * day: 1 is first of month, 31 possibly last
 * 
 */
public interface SeasonDate extends Serializable {

	/**
	 * Format: "dd.MM"
	 * @return false if argument was invalid
	 */
	public boolean setDate(String date);

	/**
	 * @return false if date = null
	 */
	public boolean setDate(Date date);

	/**
	 * 
	 * @return month, 1 for January, 12 for December, 0 if not initialized
	 */
	public int getMonth();
	
	/**
	 * 
	 * @return day, 1 for fist day of month, 31 for last, 0 if not initialized
	 */
	public int getDay();
	
	/**
	 * if the dates are same, after returns true as well (still in season)
	 * @param other date to compare with
	 * @return true if this is after other, false otherwise
	 */
	public boolean after(SeasonDate other);

	/**
	 * if the dates are same day, before() returns true as well (still in season)
	 * @param other date to compare with
	 * @return true if this is before other, false otherwise
	 */
	public boolean before(SeasonDate other);

}

