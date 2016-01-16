package fr.turri.jiso8601;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Iso8601Deserializer {
	public Calendar deserialize(String toParse){
		Calendar result = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		result.set(Calendar.HOUR_OF_DAY, 0);
		result.set(Calendar.MINUTE, 0);
		result.set(Calendar.SECOND, 0);
		result.set(Calendar.MILLISECOND, 0);
		String basicFormatDate = toParse.replaceAll("-", "");
		if ( basicFormatDate.length() == 2 ){
			result.set(Integer.parseInt(basicFormatDate) * 100, 0, 1);
			return result;
		}

		if ( basicFormatDate.length() == 4){
			result.set(Integer.parseInt(basicFormatDate), 0, 1);
			return result;
		}

		int year = Integer.parseInt(basicFormatDate.substring(0, 4));
		int month = Integer.parseInt(basicFormatDate.substring(4, 6)) - 1;
		if ( basicFormatDate.length() == 6 ){
			result.set(year, month, 1);
			return result;
		}

		if ( basicFormatDate.length() == 8 ){
			result.set(year, month, Integer.parseInt(basicFormatDate.substring(6)));
			return result;
		}
		throw new RuntimeException("Couldn't parse " + toParse);
	}
}
