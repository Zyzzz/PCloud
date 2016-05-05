package imu.pcloud.server.model;

import java.sql.Time;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Plan {
	ArrayList<String> timeStrings;
	ArrayList<String> comtextStrings;
	public ArrayList<String> getTimeStrings() {
		return timeStrings;
	}
	public void setTimeStrings(ArrayList<String> timeStrings) {
		this.timeStrings = timeStrings;
	}
	public ArrayList<String> getComtextStrings() {
		return comtextStrings;
	}
	public void setComtextStrings(ArrayList<String> comtextStrings) {
		this.comtextStrings = comtextStrings;
	}
}
