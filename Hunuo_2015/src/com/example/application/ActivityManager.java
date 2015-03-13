package com.example.application;

import java.util.LinkedList;
import java.util.List;

/**
 * 单例模式管理activity，使其安全退出
 * 
 */

import android.app.Activity;

public class ActivityManager {

	private List<Activity> activitys = null;
	private static ActivityManager instance;

	private ActivityManager() {
		activitys = new LinkedList<Activity>();
	}

	public static ActivityManager getInstance() {
		if (instance == null) {
			instance = new ActivityManager();
		}
		return instance;
	}

	public void addActivity(Activity activity) {
		if (activitys != null) {
			if (!activitys.contains(activity)) {
				activitys.add(activity);
			}
		}
	}

	public void exit() {
		if (activitys != null && activitys.size() > 0) {
			for (Activity activity : activitys) {
				activity.finish();
			}
		}
	}
}
