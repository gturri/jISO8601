package fr.turri.jiso8601;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestDeserializeTimes extends TestHelper {
	@Test
	public void canParseBasicFormatHour(){
		assertExpectedHour(12, 21, 36, "19850304T122136Z");
	}

	@Test
	public void canParseExtendedFormatHour(){
		assertExpectedHour(12, 21, 36, "1985-03-04T12:21:36Z");
	}

	@Test
	public void canParseHourWithoutSeconds(){
		assertExpectedHour(12, 21, 0, "1985-03-04T12:21Z");
	}

	@Test
	public void canParseHourWithoutMinutes(){
		assertExpectedHour(12, 0, 0, "1985-03-04T12Z");
	}

	@Test
	public void canParseHourWithDecimalPartOfASecond(){
		assertExpectedHour(12, 21, 36, 120, "1985-03-04T12:21:36.12Z");
		assertExpectedHour(12, 21, 36, 120, "1985-03-04T12:21:36,12Z");
	}

	@Test
	public void isntTooFarFromRealityWhenAVeryHighPrecisionIsProvided(){
		assertExpectedHour(12, 21, 36, 123, "1985-03-04T12:21:36.123456Z");
		assertExpectedHour(12, 21, 36, 123, "1985-03-04T12:21:36,123456Z");
	}

	@Test
	public void canParseHourWithDecimalPartOfAMinute(){
		assertExpectedHour(12, 21, 15, "1985-03-04T12:21.25Z");
		assertExpectedHour(12, 21, 30, "1985-03-04T12:21.5Z");
		assertExpectedHour(12, 21, 30, "1985-03-04T12:21,5Z");
	}

	@Test
	public void canParseHourWithDecimalPartOfAnHour() {
		assertExpectedHour(12, 15, 0, "1985-03-04T12.25");
		assertExpectedHour(12, 0, 36, "1985-03-04T12.01");
		assertExpectedHour(12, 0, 36, "1985-03-04T12,01");
	}

	@Test
	public void useLocalTimeZoneWhenNoIndicationIsProvided(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		assertExpectedHour(12, 21, 36, "1985-03-04T12:21:36");

		TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
		assertExpectedHour(20, 21, 36, "1985-03-04T12:21:36");
	}

	@Test
	public void canParseBasicFormatTimeOffset() {
		assertExpectedHour(00, 51, 36, "19850304T122136+1130");
	}

	@Test
	public void canParseExtendedFormatTimeOffset() {
		assertExpectedHour(00, 51, 36, "1985-03-04T12:21:36+11:30");
	}

	@Test
	public void canParseTimeOffsetWithoutMinute() {
		assertExpectedHour(01, 21, 36, "1985-03-04T12:21:36+11");
	}

	@Test
	public void canParseNegativeTimeOffset() {
		assertExpectedHour(21, 41, 36, "1985-03-04T12:21:36-09:20");
	}

	private void assertExpectedHour(int hour, int minute, int second, String toParse){
		assertExpectedHour(hour, minute, second, 0, toParse);
	}

	private void assertExpectedHour(int hour, int minute, int second, int millisecond, String toParse){
		Calendar expected = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		expected.set(1985, Calendar.MARCH, 04, hour, minute, second);
		expected.set(Calendar.MILLISECOND, millisecond);

		assertEquals(0, expected.compareTo(Iso8601Deserializer.toCalendar(toParse)));
		assertEquals(expected.getTime(), Iso8601Deserializer.toDate(toParse));
	}
}
