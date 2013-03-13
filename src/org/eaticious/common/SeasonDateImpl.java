package org.eaticious.common;

import java.util.Date;

/*
 * Represents a start and ending date of a season
 * month: 1 is January, 12 is December
 * day: 1 is first of month, 31 possibly last
 * 
 */
public class SeasonDateImpl implements SeasonDate {
	public int month;
	public int day;

	public SeasonDateImpl(int month, int day) {
		this.month = month;
		this.day = day;
	}

	public SeasonDateImpl() {
		month = 0;
		day = 0;
	}

	public SeasonDateImpl(Date date) {
		setDate(date);
	}

	/*
	 * Format: dd.MM
	 */
	public boolean setDate(String date) {
		String splitted[] = date.trim().split("\\.");
		if(splitted.length == 2) {
			int day = Integer.parseInt(splitted[0]);
			int month = Integer.parseInt(splitted[1]);
			if (day >= 1 && day <= 31 && month >= 1 && month <= 12) {
				this.day = day;
				this.month = month;
				return true;
			}
			else return false;
		}
		else return false;
	}

	public boolean setDate(Date date) {
		if (date != null) {
			String strDate = date.getDay() + "." + date.getMonth();
			return setDate(strDate);
		}
		else return false;
	}

	public SeasonDateImpl(SeasonDate toClone) {
		month = toClone.getMonth();
		day = toClone.getDay();
	}


	/*
	 * if the dates are same, after returns true as well (still in season)
	 */
	public boolean after(SeasonDate other){
		if (this.month > other.getMonth()) {
			return true;
		}
		else if (this.month == other.getMonth())
			if (this.day >= other.getDay())
				return true;
			else 
				return false;
		return false;
	}

	public boolean before(SeasonDate other) {
		if (this.month < other.getMonth()) {
			return true;
		}
		else if (this.month == other.getMonth())
			if (this.day <= other.getDay())
				return true;
			else 
				return false;
		return false;
	}

	@Override
	public int getMonth() {
		return this.month;
	}

	@Override
	public int getDay() {
		return this.day;
	}



}