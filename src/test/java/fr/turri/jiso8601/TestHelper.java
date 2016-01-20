package fr.turri.jiso8601;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import static org.junit.Assert.*;

public class TestHelper {
	protected void assertExpectedDate(int year, int month, int day, String toParse){
		Calendar expected = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		expected.set(year, month, day, 0, 0, 0);
		expected.set(Calendar.MILLISECOND, 0);
		assertEquals(0, expected.compareTo(new Iso8601Deserializer().deserialize(toParse)));
	}
}
