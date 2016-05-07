package imu.pcloud.server.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
	static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
	static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	public static String timeToString(Time time) {
		return timeFormat.format(time);
	}
	public static Time stringToTime(String string) {
		try {
			return (Time) timeFormat.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static String datetimeToString(Date datetime) {
		return datetimeFormat.format(datetime);
	}
	public static Date stringToDatetime(String string) {
		try {
			return datetimeFormat.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}public static String dateToString(Date date) {
		return dateFormat.format(date);
	}
	public static Date stringToDate(String string) {
		try {
			return dateFormat.parse(string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
