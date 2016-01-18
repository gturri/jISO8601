package fr.turri.jiso8601;

import java.util.Calendar;

import org.junit.Test;

public class TestDeserializeWeekDates extends TestHelper {
	@Test
	public void canParseBasicFormat(){
		assertExpectedDate(1985, Calendar.MARCH, 04, "1985W101");
	}

	@Test
	public void canParseExtendedFormat(){
		assertExpectedDate(1985, Calendar.MARCH, 04, "1985-W10-1");
	}

	@Test
	public void canParseDateWithoutDay(){
		assertExpectedDate(1985, Calendar.MARCH, 04, "1985-W10");
	}

	@Test
	public void testBoundariesConditions(){
		assertExpectedDate(2016, Calendar.JANUARY, 04, "2016-W01-1");
	}
}
