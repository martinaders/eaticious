package org.eaticious.common;

import java.util.Date;

/**
 * Represents a start and ending date of a season
 * Much more convenient than util.Date because not needing a specific Time, comparing better (before, after)
 * 
 * month: 1 is January, 12 is December
 * day: 1 is first of month, 31 possibly last
 * 
 */
public interface SeasonDateImpl {


	public boolean setDate(String date);

	public boolean setDate(Date date);

	
	/**
	 * if the dates are same, after returns true as well (still in season)
	 */
	public boolean after(SeasonDateImpl other);

	public boolean before(SeasonDateImpl other);

}

