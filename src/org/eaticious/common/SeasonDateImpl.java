package org.eaticious.common;

import java.util.Date;

/*
 * Represents a start and ending date of a season
 * month: 1 is January, 12 is December
 * day: 1 is first of month, 31 possibly last
 * 
 */
public class SeasonDateImpl implements SeasonDate {

	private static final long serialVersionUID = -2858234034518875678L;

	public int month;
	public int day;

	public SeasonDateImpl(final int month, final int day) {
		this.month = month;
		this.day = day;
	}

	public SeasonDateImpl() {
		month = 0;
		day = 0;
	}

	public SeasonDateImpl(final Date date) {
		setDate(date);
	}

	// copy constructor
	public SeasonDateImpl(final SeasonDate toClone) {
		month = toClone.getMonth();
		day = toClone.getDay();
	}

	/*
	 * Format: dd.MM
	 */
	@Override
	public boolean setDate(final String date) {
		boolean result = false;
		final String splitted[] = date.trim().split("\\.");
		if (splitted.length == 2) {
			final int day = Integer.parseInt(splitted[0]);
			final int month = Integer.parseInt(splitted[1]);
			if (day >= 1 && day <= 31 && month >= 1 && month <= 12) {
				this.day = day;
				this.month = month;
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean setDate(final Date date) {
		boolean result = false;
		if (date != null) {
			String strDate = date.getDay() + "." + date.getMonth();
			result = setDate(strDate);
		}
		return result;
	}

	/*
	 * if the dates are same, after returns true as well (still in season)
	 */
	@Override
	public boolean after(final SeasonDate other) {
		boolean monthAfter = this.month > other.getMonth();
		boolean dayAfter = this.month == other.getMonth() && this.day >= other.getDay();
		return monthAfter || dayAfter;
	}

	@Override
	public boolean before(final SeasonDate other) {
		boolean monthBefore = this.month < other.getMonth();
		boolean dayBefore = this.month == other.getMonth() && this.day <= other.getDay(); 
		return  monthBefore || dayBefore;
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