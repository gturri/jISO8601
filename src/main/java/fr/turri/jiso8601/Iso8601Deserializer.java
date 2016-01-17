package fr.turri.jiso8601;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Iso8601Deserializer {
	public Calendar deserialize(String toParse){
		if ( toParse.indexOf('T') == -1 ){
			return calendarWithDateOnly(toParse);
		}
		int indexOfT = toParse.indexOf('T');
		Calendar result = calendarWithDateOnly(toParse.substring(0, indexOfT));
		String basicFormatHour = toParse.substring(indexOfT+1).replace(":", "");

		int indexOfZ = basicFormatHour.indexOf('Z');
		if ( indexOfZ != -1 ){
			parseHour(result, basicFormatHour.substring(0, indexOfZ));
		} else {
			int indexOfSign = getIndexOfSign(basicFormatHour);
			if ( indexOfSign == -1 ){
				parseHour(result, basicFormatHour);
				result.setTimeZone(TimeZone.getDefault());
			} else {
				parseHour(result, basicFormatHour.substring(0, indexOfSign));
				parseTimeZone(result, basicFormatHour.substring(indexOfSign));
			}
		}
		return result;
	}

	private static int getIndexOfSign(String str){
		int index = str.indexOf('+');
		return index != -1 ? index : str.indexOf('-');
	}

	private void parseTimeZone(Calendar calendar, String tzStr){
		calendar.setTimeZone(TimeZone.getTimeZone("GMT" + tzStr));
	}

	private void parseHour(Calendar calendar, String basicFormatHour){
		basicFormatHour = basicFormatHour.replace(',', '.');
		int indexOfDot = basicFormatHour.indexOf('.');
		double fractionalPart = 0;
		if ( indexOfDot != -1 ){
			fractionalPart = Double.parseDouble("0" + basicFormatHour.substring(indexOfDot));
			basicFormatHour = basicFormatHour.substring(0, indexOfDot);
		}

		if ( basicFormatHour.length() >= 2 ){
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(basicFormatHour.substring(0, 2)));
		}

		if ( basicFormatHour.length() > 2 ){
			calendar.set(Calendar.MINUTE, Integer.parseInt(basicFormatHour.substring(2, 4)));
		} else {
			fractionalPart *= 60;
		}

		if ( basicFormatHour.length() > 4 ){
			calendar.set(Calendar.SECOND, Integer.parseInt(basicFormatHour.substring(4, 6)));
		} else {
			fractionalPart *= 60;
		}

		calendar.set(Calendar.MILLISECOND, (int) (fractionalPart * 1000));
	}

	private static Calendar calendarWithDateOnly(String dateStr){
			Calendar result = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
			result.set(Calendar.HOUR_OF_DAY, 0);
			result.set(Calendar.MINUTE, 0);
			result.set(Calendar.SECOND, 0);
			result.set(Calendar.MILLISECOND, 0);
			String basicFormatDate = dateStr.replaceAll("-", "");

			if ( basicFormatDate.indexOf('W') != -1 ){
				return parseWeekDate(result, basicFormatDate);
			}

			if ( basicFormatDate.length() == 7 ){
				return parseOrdinalDate(result, basicFormatDate);
			}

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
			throw new RuntimeException("Couldn't parse " + dateStr);
	}

	private static Calendar parseWeekDate(Calendar result, String basicFormatDate) {
		result.set(Calendar.YEAR, Integer.parseInt(basicFormatDate.substring(0, 4)));
		result.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(basicFormatDate.substring(5, 7)));
		result.set(Calendar.DAY_OF_WEEK, basicFormatDate.length() == 7 ? 2 : (Integer.parseInt(basicFormatDate.substring(7)) + 1));
		return result;
	}

	private static Calendar parseOrdinalDate(Calendar calendar, String basicFormatOrdinalDate) {
		calendar.set(Calendar.YEAR, Integer.parseInt(basicFormatOrdinalDate.substring(0, 4)));
		calendar.set(Calendar.DAY_OF_YEAR, Integer.parseInt(basicFormatOrdinalDate.substring(4)));
		return calendar;
	}
}
