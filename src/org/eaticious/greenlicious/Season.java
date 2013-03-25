package org.eaticious.greenlicious;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eaticious.common.SeasonType;

public interface Season {
	
	GregorianCalendar getBeginning();
	
	void setBeginning(GregorianCalendar beginning);
	
	void setBeginning(Date beginning);
	
	void setBeginning(int day, int month);
	
	void setBeginning(int month);
	
	GregorianCalendar getEnd();
	
	void setEnd(GregorianCalendar end);
	
	void setEnd(Date beginning);
	
	void setEnd(int day, int month);
	
	void setEnd(int month);
	
	SeasonType getSeasonType();
	
	void setSeasonType(SeasonType type);
	
	boolean isInSeason(GregorianCalendar cal);
	
	boolean isInSeason(Date date);

	GregorianCalendar makeCalendar(String datestring, SimpleDateFormat format) throws ParseException;

}
