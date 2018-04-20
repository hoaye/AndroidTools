package com.qianlong.android.util;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qianlong.android.bean.FunctionList.FunctionItem;
import com.qianlong.android.bean.NewsCenterCategories.ChildNewsCate;

public class GsonTools {

	public GsonTools() {
		// TODO Auto-generated constructor stub
	}

	public static String createGsonString(Object object) {
		Gson gson = new Gson();
		String gsonString = gson.toJson(object);
		return gsonString;
	}

	public static <T> T changeGsonToBean(String gsonString, Class<T> cls) {
		Gson gson = new Gson();
		T t = gson.fromJson(gsonString, cls);
		return t;
	}

	public static <T> List<T> changeGsonToList(String gsonString, Class<T> cls) {
		Gson gson = new Gson();
		List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
		}.getType());
		return list;
	}

	public static List<FunctionItem> changeGsonToFunList(String gsonString,
			Class<FunctionItem> cls) {
		Gson gson = new Gson();
		List<FunctionItem> list = gson.fromJson(gsonString,
				new TypeToken<List<FunctionItem>>() {
				}.getType());
		return list;
	}
	
	public static List<ChildNewsCate> changeGsonToCateList(String gsonString,
			Class<ChildNewsCate> cls) {
		Gson gson = new Gson();
		List<ChildNewsCate> list = gson.fromJson(gsonString,
				new TypeToken<List<ChildNewsCate>>() {
				}.getType());
		return list;
	}

	public static <T> List<Map<String, T>> changeGsonToListMaps(
			String gsonString) {
		List<Map<String, T>> list = null;
		Gson gson = new Gson();
		list = gson.fromJson(gsonString, new TypeToken<List<Map<String, T>>>() {
		}.getType());
		return list;
	}

	public static <T> Map<String, T> changeGsonToMaps(String gsonString) {
		Map<String, T> map = null;
		Gson gson = new Gson();
		map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
		}.getType());
		return map;
	}

}
