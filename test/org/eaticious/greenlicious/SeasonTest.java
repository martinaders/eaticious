package org.eaticious.greenlicious;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.text.DateFormatter;

import org.eaticious.common.SeasonType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sun.util.calendar.CalendarUtils;

public class SeasonTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	private Season season;

	@Before
	public void setUp() throws Exception {
		this.season = new SeasonImpl();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetBeginning() {
		Integer expMonth = Calendar.JANUARY;
		Integer expDay = 1;
		GregorianCalendar setter = new GregorianCalendar(2012, expMonth, expDay);
		this.season.setBeginning(setter);
		
		Double expected = this.makeDateComparator(setter);
		Double actual = this.makeDateComparator(this.season.getBeginning()); 
		
		assertEquals(expected, actual);

	}

	@Test
	public void testGetEnd() {
		Integer expMonth = Calendar.JANUARY;
		Integer expDay = 1;
		GregorianCalendar setter = new GregorianCalendar(2012, expMonth, expDay);
		this.season.setEnd(setter);
		
		Double expected = this.makeDateComparator(setter);
		Double actual = this.makeDateComparator(this.season.getEnd());
		
		assertEquals(expected, actual);
	}

	@Test
	public void testGetSeasonType() {
		this.season.setSeasonType(SeasonType.OUT_OF_SEASON);
		assertEquals(SeasonType.OUT_OF_SEASON, this.season.getSeasonType());
	}

	@Test
	public void testIsInSeasonCalendar1() {
		GregorianCalendar start = new GregorianCalendar(2004, Calendar.FEBRUARY, 1);
		this.season.setBeginning(start);
		
		GregorianCalendar end = new GregorianCalendar(2004, Calendar.MAY, 30);
		this.season.setEnd(end);
		
		GregorianCalendar cal = new GregorianCalendar(2002, Calendar.MARCH, 2);
		assertTrue(this.season.isInSeason(cal));
	}

	@Test
	public void testIsInSeasonCalendar2() {
		GregorianCalendar start = new GregorianCalendar(2004, Calendar.FEBRUARY, 29);
		this.season.setBeginning(start);
		
		GregorianCalendar end = new GregorianCalendar(2004, Calendar.MAY, 30);
		this.season.setEnd(end);
		
		GregorianCalendar cal = new GregorianCalendar(1996, Calendar.FEBRUARY, 29);
		assertTrue(this.season.isInSeason(cal));
	}
	
	@Test
	public void testIsInSeasonCalendar3() {
		GregorianCalendar start = new GregorianCalendar(2004, Calendar.FEBRUARY, 1);
		this.season.setBeginning(start);
		
		GregorianCalendar end = new GregorianCalendar(2004, Calendar.MAY, 30);
		this.season.setEnd(end);
		
		GregorianCalendar cal = new GregorianCalendar(1996, Calendar.FEBRUARY, 1);
		assertTrue(this.season.isInSeason(cal));
	}
	
	@Test
	public void testIsInSeasonCalendar4() {
		GregorianCalendar start = new GregorianCalendar(2004, Calendar.FEBRUARY, 1);
		this.season.setBeginning(start);
		
		GregorianCalendar end = new GregorianCalendar(2004, Calendar.MAY, 30);
		this.season.setEnd(end);
		
		GregorianCalendar cal = new GregorianCalendar(1996, Calendar.MAY, 30);
		assertTrue(this.season.isInSeason(cal));
	}
	
	@Test
	public void testIsInSeasonCalendar5() {
		GregorianCalendar start = new GregorianCalendar(2004, Calendar.JANUARY, 1);
		this.season.setBeginning(start);
		
		GregorianCalendar end = new GregorianCalendar(2004, Calendar.FEBRUARY, 28);
		this.season.setEnd(end);
		
		GregorianCalendar cal = new GregorianCalendar(1996, Calendar.FEBRUARY, 29);
		assertTrue(this.season.isInSeason(cal));
	}
	
	@Test
	public void testIsInSeasonCalendar6() {
		GregorianCalendar start = new GregorianCalendar(2004, Calendar.FEBRUARY, 1);
		this.season.setBeginning(start);
		
		GregorianCalendar end = new GregorianCalendar(2004, Calendar.MAY, 30);
		this.season.setEnd(end);
		
		GregorianCalendar cal = new GregorianCalendar(1996, Calendar.JANUARY, 31);
		assertFalse(this.season.isInSeason(cal));
	}
	
	@Test
	public void testIsInSeasonCalendar7() {
		GregorianCalendar start = new GregorianCalendar(2004, Calendar.FEBRUARY, 1);
		this.season.setBeginning(start);
		
		GregorianCalendar end = new GregorianCalendar(2004, Calendar.MAY, 30);
		this.season.setEnd(end);
		
		GregorianCalendar cal = new GregorianCalendar(1996, Calendar.MAY, 31);
		assertFalse(this.season.isInSeason(cal));
	}
	
	@Test
	public void testIsInSeasonDate1() {
		GregorianCalendar start = new GregorianCalendar(2004, Calendar.FEBRUARY, 1);
		this.season.setBeginning(start);
		
		GregorianCalendar end = new GregorianCalendar(2004, Calendar.MAY, 30);
		this.season.setEnd(end);
		
		Date date = new GregorianCalendar(1996, Calendar.MAY, 31).getTime();
		assertFalse(this.season.isInSeason(date));
	}

	@Test
	public void testIsInSeasonDate2() {
		GregorianCalendar start = new GregorianCalendar(2004, Calendar.FEBRUARY, 1);
		this.season.setBeginning(start);
		
		GregorianCalendar end = new GregorianCalendar(2004, Calendar.MAY, 30);
		this.season.setEnd(end);
		
		Date date = new GregorianCalendar(1996, Calendar.MARCH, 31).getTime();
		assertTrue(this.season.isInSeason(date));
	}
	
	@Test
	public void testMakeCalendar() {
		int day = 23;
		int month = 9; // month is 10-1 (January is 0)
		int year = 2013;
		String date = "2013-10-23";
		String format = "yyyy-M-dd";
		try {
			Calendar cal = this.season.makeCalendar(date, new SimpleDateFormat(format));
			assertTrue(year == cal.get(Calendar.YEAR) && month == cal.get(Calendar.MONTH) && day == cal.get(Calendar.DATE));
		} catch (ParseException e) {
			fail("Exception thrown\n" + e);
			e.printStackTrace();
		}
	}

	@Test
	public void testSetBeginningIntegerInteger() {
		int day = 13;
		int month = 7;
		this.season.setBeginning(day, month);
		Calendar cal = this.season.getBeginning();
		assertTrue(cal.get(Calendar.DATE) == day && cal.get(Calendar.MONTH) == month);
	}

	@Test
	public void testSetEndDate() {
		GregorianCalendar cal = new GregorianCalendar(2003, Calendar.MARCH, 21, 0, 12, 59);
		this.season.setEnd(cal.getTime());
		GregorianCalendar actual = this.season.getEnd();
		assertTrue(cal.get(Calendar.MONTH) == actual.get(Calendar.MONTH) && cal.get(Calendar.DATE) == actual.get(Calendar.DATE));
	}

	@Test
	public void testSetEndIntegerInteger() {
		int day = 13;
		int month = 7;
		GregorianCalendar calex = new GregorianCalendar(2001, month, day, 0, 0, 0);
		this.season.setEnd(day, month);
		Calendar cal = this.season.getEnd();
		assertTrue(cal.get(Calendar.DATE) == day && cal.get(Calendar.MONTH) == month);
	}

	private Double makeDateComparator(Calendar cal){
		return cal.get(Calendar.MONTH) * 1e+3 + cal.get(Calendar.DATE);
	}

}
