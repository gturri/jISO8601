package fr.turri.jiso8601;

import java.util.Calendar;

import org.junit.Test;

public class TestDeserializeOrdinalDates extends TestHelper {
	@Test
	public void canParseBasicFormat(){
		assertExpectedDate(1985, Calendar.MARCH, 04, "1985063");
	}

	@Test
	public void canParseExtendedFormat(){
		assertExpectedDate(1985, Calendar.MARCH, 04, "1985-063");
	}
}
