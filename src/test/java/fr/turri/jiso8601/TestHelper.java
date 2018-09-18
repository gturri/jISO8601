package fr.turri.jiso8601;

import org.junit.After;
import org.junit.Before;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHelper {
	private TimeZone _previousTZ;

	@Before
	public void setUp(){
		_previousTZ = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@After
	public void tearDown(){
		TimeZone.setDefault(_previousTZ);
	}

	void assertExpectedDate(int year, int month, int day, String toParse){
		Calendar expected = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		expected.set(year, month, day, 0, 0, 0);
		expected.set(Calendar.MILLISECOND, 0);
		expected.setMinimalDaysInFirstWeek(4);
		expected.setFirstDayOfWeek(Calendar.MONDAY);

		assertThat(Iso8601Deserializer.toCalendar(toParse)).isEqualTo(expected);
		assertThat(Iso8601Deserializer.toDate(toParse)).isEqualTo(expected.getTime());
	}
}
