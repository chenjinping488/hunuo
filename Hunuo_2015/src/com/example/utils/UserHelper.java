package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class UserHelper {

	private static UserHelper saveUser = null;
	Context context;
	public SharedPreferences preferences;
	public SharedPreferences.Editor editor;

	private UserHelper(Context context) {
		this.context = context;
		preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		editor = context.getSharedPreferences("user", Context.MODE_PRIVATE).edit();
	}

	public static UserHelper getInstance(Context context) {
		if (saveUser == null) {
			saveUser = new UserHelper(context);
		}
		return saveUser;
	}

}
