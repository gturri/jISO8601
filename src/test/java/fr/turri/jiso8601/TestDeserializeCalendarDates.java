package fr.turri.jiso8601;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.*;

public class TestDeserializeCalendarDates {
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

	private void assertExpectedDate(int year, int month, int day, String toParse){
		Calendar expected = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		expected.set(year, month, day, 0, 0, 0);
		expected.set(Calendar.MILLISECOND, 0);
		assertEquals(expected, new Iso8601Deserializer().deserialize(toParse));
	}

}
