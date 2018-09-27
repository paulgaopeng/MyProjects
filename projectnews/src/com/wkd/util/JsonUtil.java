package com.wkd.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class JsonUtil {

	/**
	 * ����* ��һ��JSON �����ַ���ʽ�еõ�һ��java���� ����
	 */
	public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
		Object pojo;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		pojo = JSONObject.toBean(jsonObject, pojoCalss);
		return pojo;
	}
	
	public static Object getObject4JsonString(String jsonString, Class pojoCalss,String dateFormat) {
		Object pojo;
		JsonConfig jsonConfig = configJson(dateFormat);
		JSONObject jsonObject = JSONObject.fromObject(jsonString,jsonConfig);
		pojo = JSONObject.toBean(jsonObject, pojoCalss);
		return pojo;
	}

	/**
	 * ��json HASH���ʽ�л�ȡһ��map����map֧��Ƕ�׹���
	 * */
	public static Map getMap4Json(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator keyIter = jsonObject.keys();
		String key;
		Object value;
		Map valueMap = new HashMap();
		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key);
			valueMap.put(key, value);
		}
		return valueMap;
	}
	
	
	/**
	 * ��Map��ȡһ��jsonString
	 * @param map
	 * @return
	 */
	public static String getJsonString4Map(Map<String,String> map) {
		 Set<String> keys = map.keySet();  
	        String key = "";  
	        String value = "";  
	        StringBuffer jsonBuffer = new StringBuffer();  
	        jsonBuffer.append("{");  
	        for (Iterator<String> it = keys.iterator(); it.hasNext();) {  
	            key = (String) it.next();  
	            value = map.get(key);  
	            jsonBuffer.append(key + ":" +"\""+ value+"\"");  
	            if (it.hasNext()) {  
	                jsonBuffer.append(",");  
	            }  
	        }  
	        jsonBuffer.append("}");  
	        return jsonBuffer.toString();  
	}

	/***
	 * ��json�����еõ���Ӧjava���� ����
	 */
	public static Object[] getObjectArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}

	/**
	 * ����* ��json���󼯺ϱ��ʽ�еõ�һ��java�����б�
	 */
	public static List getList4Json(String jsonString, Class pojoClass) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JSONObject jsonObject;
		Object pojoValue;
		List list = new ArrayList();
		for (int i = 0; i < jsonArray.size(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			pojoValue = JSONObject.toBean(jsonObject, pojoClass);
			list.add(pojoValue);
		}
		return list;
	}

	/**
	 * ����* ��json�����н�����java�ַ������� ����
	 */
	public static String[] getStringArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		String[] stringArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			stringArray[i] = jsonArray.getString(i);
		}
		return stringArray;
	}

	/**
	 * ��json�����н�����javaLong�Ͷ�������
	 */
	public static Long[] getLongArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Long[] longArray = new Long[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			longArray[i] = jsonArray.getLong(i);
		}
		return longArray;
	}

	/*** ��json�����н�����java Integer�Ͷ������� */
	public static Integer[] getIntegerArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Integer[] integerArray = new Integer[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			integerArray[i] = jsonArray.getInt(i);
		}
		return integerArray;
	}

	/**
	 * ��json�����н�����java Date �Ͷ������飬ʹ�ñ��������뱣֤ ����
	 */
	public static Date[] getDateArray4Json(String jsonString, String DataFormat) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Date[] dateArray = new Date[jsonArray.size()];
		String dateString;
		Date date;
		for (int i = 0; i < jsonArray.size(); i++) {
			dateString = jsonArray.getString(i);
			date = DateJsonValueProcessor.stringToDate(dateString, DataFormat);
			dateArray[i] = date;
		}
		return dateArray;
	}

	/*** ��json�����н�����java Integer�Ͷ������� */
	public static Double[] getDoubleArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Double[] doubleArray = new Double[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			doubleArray[i] = jsonArray.getDouble(i);
		}
		return doubleArray;
	}

	/**
	 * ��java����ת����json�ַ��� ��
	 */
	public static String getJsonString4JavaPOJO(Object javaObj) {

		JSONObject json;
		json = JSONObject.fromObject(javaObj);
		return json.toString();

	}

	/**
	 * ��java����ת����json�ַ���,���趨���ڸ�ʽ ����
	 */

	public static String getJsonString4JavaPOJO(Object javaObj,
			String dataFormat) {

		JSONObject json;
		JsonConfig jsonConfig = configJson(dataFormat);
		json = JSONObject.fromObject(javaObj, jsonConfig);

		return json.toString();

	}
	
	

	/**
	 * ��Listת����json�ַ��� ��
	 */
	public static String getJsonString4List(List ObjList) {

		JSONArray json;
		json = JSONArray.fromObject(ObjList);
		return json.toString();

	}
	
	/**
	 * ��Listת����json�ַ��� �����趨���ڸ�ʽ ����
	 */
	public static String getJsonString4List(List ObjList,String dataFormat) {

		JSONArray json;
		JsonConfig jsonConfig = configJson(dataFormat);
		json = JSONArray.fromObject(ObjList,jsonConfig);
		return json.toString();

	}
	
	
	//�����첽��Ӧ���ͻ��������
	public static void ajaxResponse(HttpServletResponse response,String jsonStr){

		try {
			//����ajax��Ӧ
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			response.getWriter().write(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * ����* JSON ʱ���������
	 * 
	 * ����* @param datePattern
	 * 
	 * ����* @return
	 * 
	 * ����
	 */

	public static JsonConfig configJson(String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "" });
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
		new DateJsonValueProcessor(datePattern));
		return jsonConfig;
	}

	public static JsonConfig configJson(String[] excludes, String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor(datePattern));
		return jsonConfig;
	}
}
