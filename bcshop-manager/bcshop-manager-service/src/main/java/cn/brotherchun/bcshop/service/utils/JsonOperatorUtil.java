package cn.brotherchun.bcshop.service.utils;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * json解析工具类
 * 
 * @author brotherchun
 * 
 */
public class JsonOperatorUtil {
	public static JSONObject toJSONObject(String str) {
		return (JSONObject) JSONValue.parse(str);
	}

	public static JSONArray toJSONArray(String str) {
		return (JSONArray) JSONValue.parse(str);
	}
}
