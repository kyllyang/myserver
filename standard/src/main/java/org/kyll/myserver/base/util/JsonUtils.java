package org.kyll.myserver.base.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Exchanger;

/**
 * User: Kyll
 * Date: 2014-11-07 13:16
 */
public class JsonUtils {
	private static final JsonConfig JSON_CONFIG = new JsonConfig();

	static {
		JSON_CONFIG.registerDefaultValueProcessor(Byte.class, aClass -> null);
		JSON_CONFIG.registerDefaultValueProcessor(Character.class, aClass -> null);
		JSON_CONFIG.registerDefaultValueProcessor(Double.class, aClass -> null);
		JSON_CONFIG.registerDefaultValueProcessor(Float.class, aClass -> null);
		JSON_CONFIG.registerDefaultValueProcessor(Integer.class, aClass -> null);
		JSON_CONFIG.registerDefaultValueProcessor(Long.class, aClass -> null);
		JSON_CONFIG.registerDefaultValueProcessor(Short.class, aClass -> null);
	}

	public static String convert(Object object) {
		return JSONObject.fromObject(object, JSON_CONFIG).toString();
	}

	public static String convert(List list) {
		return JSONArray.fromObject(list, JSON_CONFIG).toString();
	}

	public static <T> String convertTree(List<T> list, TreeNodeHandler<T> treeNodeHandler) {
		JSONArray ja = new JSONArray();
		for (T t : list) {
			JSONObject jo = new JSONObject();
			treeNodeHandler.handler(jo, t);
			ja.add(jo);
		}
		return ja.toString();
	}

	public static String ajaxResult(boolean success) {
		Map<String, Object> map = new HashMap<>();
		map.put("success", success);
		return JSONObject.fromObject(map, JSON_CONFIG).toString();
	}

	public static Long getLong(JSONObject jo, String name) {
		Long result = null;
		if (jo.has(name)) {
			try {
				result = jo.getLong(name);
			} catch (JSONException ignored) {
			}
		}
		return result;
	}

	public static String getString(JSONObject jo, String name) {
		String result = null;
		if (jo.has(name)) {
			result = jo.getString(name);
		}
		return result;
	}

	public static Integer getInteger(JSONObject jo, String name) {
		Integer result = null;
		if (jo.has(name)) {
			try {
				result = jo.getInt(name);
			} catch (JSONException ignored) {
			}
		}
		return result;
	}

	public interface TreeNodeHandler<T> {
		void handler(JSONObject jo, T t);
	}

	private JsonUtils() {
	}
}
