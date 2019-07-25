package fr.turri.jiso8601;

import java.util.Calendar;

import org.junit.Test;

public class TestDeserializeWeekDates extends TestHelper {
	@Test
	public void canParseBasicFormat(){
		assertExpectedDate(1985, Calendar.MARCH, 4, "1985W101");
	}

	@Test
	public void canParseExtendedFormat(){
		assertExpectedDate(1985, Calendar.MARCH, 4, "1985-W10-1");
	}

	@Test
	public void canParseDateWithoutDay(){
		assertExpectedDate(1985, Calendar.MARCH, 4, "1985-W10");
	}

	@Test
	public void testBoundariesConditions(){
		assertExpectedDate(2016, Calendar.JANUARY, 4, "2016-W01-1");
		assertExpectedDate(2014, Calendar.DECEMBER, 29, "2015-W01-1");
		assertExpectedDate(1994, Calendar.DECEMBER, 31, "1994-W52-6");
		assertExpectedDate(1995, Calendar.JANUARY, 1, "1994-W52-7");
		assertExpectedDate(1996, Calendar.DECEMBER, 31, "1997-W01-2");
	}
}
