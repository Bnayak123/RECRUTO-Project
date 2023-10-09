package com.tyssSpark.recrutounitservice.util;

import com.tyssSpark.recrutounitservice.exception.MaliciousCodeFoundException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.stream.Collectors;



public class XssCleanUtil {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public static Object santize(Object obj,boolean b) {
		
		if (obj != null && !obj.getClass().isPrimitive()) {
			if (obj instanceof String) {
				if(((String) obj).contains("<xssCustomTag>")) {
                    return ((String) obj).replace("<xssCustomTag>", "").replace("</xssCustomTag>", "");
                }
				return encode((String) obj, b);
			} else if (obj != null && obj.getClass().isArray()) {
				Object arr[] = (Object[]) obj;
				for (int i = 0; i < arr.length; i++) {
					arr[i] = santize(arr[i], b);
				}
				return obj;

			} else if (obj != null && obj instanceof Collection) {
				if (obj instanceof TreeSet) {
					return ((Collection) obj).stream().map(val -> santize(val, b))
							.collect(Collectors.toCollection(TreeSet::new));
				} else if (obj instanceof LinkedHashSet) {
					return ((Collection) obj).stream().map(val -> santize(val, b))
							.collect(Collectors.toCollection(LinkedHashSet::new));
				} else if (obj instanceof HashSet) {
					return ((Collection) obj).stream().map(val -> santize(val, b))
							.collect(Collectors.toCollection(HashSet::new));
				} else if (obj instanceof LinkedList) {
					return ((Collection) obj).stream().map(val -> santize(val, b))
							.collect(Collectors.toCollection(LinkedList::new));
				} else if (obj instanceof ArrayList) {
					return ((Collection) obj).stream().map(val -> santize(val, b))
							.collect(Collectors.toCollection(ArrayList::new));
				}

				return obj;
			} else if (obj != null && obj instanceof Map) {
				if (obj instanceof TreeMap) {
					return mapCheck1((Map) obj, TreeMap::new , b);
				}
				if (obj instanceof LinkedHashMap) {
					return mapCheck1((Map) obj, LinkedHashMap::new , b);
				}
				if (obj instanceof HashMap) {
					return mapCheck1((Map) obj, HashMap::new , b);
				}
				return obj;
			}

			else if (obj != null && (obj.getClass().getName().contains("package name") || obj.getClass().getName().contains("package name"))) {
				Field[] declaredFields = obj.getClass().getDeclaredFields();
				for (Field field : declaredFields) {
					field.setAccessible(true);
					try {
						Object fieldValue = field.get(obj);
						if (fieldValue != null && fieldValue instanceof String)
							field.set(obj, encode(fieldValue.toString(), b));
						else
							if(!field.getName().equals("serialVersionUID"))
							{
								field.set(obj, santize(fieldValue, b));
							}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				return obj;
			}
		}
		return obj;
	}

	static private Object mapCheck1(Map<Object, Object> inputMap, Supplier<Map<Object, Object>> supplier,
			boolean checkMalicious) {

		Map<Object, Object> map = supplier.get();
		Set<Object> keys = inputMap.keySet();
		for (Object key : keys) {
			map.put(santize(key, checkMalicious), santize(inputMap.get(key), checkMalicious));
		}
		return map;
	}

	private static String checkMalicious(String value) throws MaliciousCodeFoundException

	{

		Pattern r = Pattern.compile(
				"((?i)javascript:.*)|((?i)vbscript:.*)|((?i)(void|eval|expression)\\(.*?\\).*)|(<.+?>)|((?i)(src|rel|href).*\\=.*)");
		// Now create matcher object.
		Matcher m = r.matcher(value);
		if (m.find()) {
			throw new MaliciousCodeFoundException("Unsupported Characters found in"+" "+value+" "+ "Please enter the valid data");
			
		}

		else
			return value;
	}
	
	private static  String encode(String value,boolean checkMalicious)
	{
		if(checkMalicious) {
			checkMalicious(value);
		}
		return value.replaceAll("(?i)javascript:.*", "")
	     .replaceAll("(?i)vbscript:.*", "")
	     .replaceAll("(?i)(void|eval|expression)\\(.*?\\).*", "")
	     .replaceAll("(?i)<.*?>.*?</.*?>", "")
	     .replaceAll("(?i)</?.*?/?>.*?", "")
             .replaceAll("(?i)(src|rel|href).*\\=.*", "")
             .replaceAll("<", "&lt;")
             .replaceAll(">", "&gt;")
	     .replaceAll("'", "&#39")
	     .replaceAll("\"", "&quot;")
	     .replaceAll("&", "&amp;");
	
		
			
	}

}
