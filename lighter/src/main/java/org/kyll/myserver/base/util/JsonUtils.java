package org.kyll.myserver.base.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.processors.JsonValueProcessor;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		JSON_CONFIG.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
			@Override
			public Object processArrayValue(Object o, JsonConfig jsonConfig) {
				return this.process(o);
			}

			@Override
			public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
				return this.process(o);
			}

			private Object process(Object value){
				if (value instanceof Date) {
					return DateFormatUtils.format((Date) value, ConstUtils.DATETIME_FORMAT_FULL);
				}
				return value == null ? "" : value.toString();
			}
		});
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
			result = jo.getInt(name);
		}
		return result;
	}

	public interface TreeNodeHandler<T> {
		void handler(JSONObject jo, T t);
	}

	private JsonUtils() {
	}
}
