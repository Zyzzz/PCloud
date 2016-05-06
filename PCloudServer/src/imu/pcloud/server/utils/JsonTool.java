package imu.pcloud.server.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

abstract public class JsonTool {
	static Gson gson = new GsonBuilder().create();
	
	public static String objToJsonString(Object o) {
		return gson.toJson(o);
	}
	
	public static Object jsonStringOToObj(String jsonString) {
		
		return null;
	}
}
