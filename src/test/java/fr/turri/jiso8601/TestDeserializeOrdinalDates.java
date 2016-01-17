package fr.turri.jiso8601;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDeserializeOrdinalDates {
	@Test
	public void canParseBasicFormat(){
		assertExpectedDate(1985, Calendar.MARCH, 04, "1985063");
	}

	@Test
	public void canParseExtendedFormat(){
		assertExpectedDate(1985, Calendar.MARCH, 04, "1985-063");
	}

	private void assertExpectedDate(int year, int month, int day, String toParse){
		Calendar expected = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		expected.set(year, month, day, 0, 0, 0);
		expected.set(Calendar.MILLISECOND, 0);
		assertEquals(expected, new Iso8601Deserializer().deserialize(toParse));
	}
}
