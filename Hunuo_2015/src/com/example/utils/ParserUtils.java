package com.example.utils;

import java.util.List;

import com.example.entity.Banner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class ParserUtils {

	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

	public static List<Banner> parserBanner(String json) {
		JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
		List<Banner> map = gson.fromJson(jsonArray.toString(), new TypeToken<List<Banner>>() {
		}.getType());
		return map;
	}
}
