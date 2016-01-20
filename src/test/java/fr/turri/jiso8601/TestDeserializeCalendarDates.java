package fr.turri.jiso8601;

import java.util.Calendar;

import org.junit.*;

public class TestDeserializeCalendarDates extends TestHelper {
	@Test
	public void canParseBasicFormat(){
		assertExpectedDate(1985, Calendar.MARCH, 04, "19850304");
	}

	@Test
	public void canParseExtendedFormat(){
		assertExpectedDate(1985, Calendar.MARCH, 04, "1985-03-04");
	}

	@Test
	public void canParseBasicFormatWithoutDay(){
		assertExpectedDate(1985, Calendar.MARCH, 01, "198503");
	}

	@Test
	public void canParseExtendedFormatWithoutDay(){
		assertExpectedDate(1985, Calendar.MARCH, 01, "1985-03");
	}

	@Test
	public void canParseBasicFormatWithYearOnly(){
		assertExpectedDate(1985, Calendar.JANUARY, 01, "1985");
	}

	@Test
	public void canParseBasicFormatWithCentury(){
		assertExpectedDate(1900, Calendar.JANUARY, 01, "19");
	}
}