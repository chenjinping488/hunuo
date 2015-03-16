package com.example.utils;

import java.util.List;

import com.example.entity.Left;

public class Constants {

	/******************* URL *********************/
	// public static String APP_URL = "http://hunuoapp.gz1.hostadm.net/";
	public static String APP_URL = "http://192.168.0.132:8004/";

	public final static String BANNER_URL = APP_URL + "index.aspx";

	public final static String Top_URL = APP_URL + "/app/GetTopmenu.aspx";

	public final static String VIDEO_URL = APP_URL + "/app/GetVideo.aspx";

	public final static String REGISTER_URL = APP_URL + "/app/GetRegister.aspx";

	public final static String DOCTOR_URL = APP_URL + "/app/GetDoctor.aspx";

	public final static String LOGIN_URL = APP_URL + "/login.aspx";

	public final static String DOWNLOAD_URL = "http://www.17cycle.cn/app/bike.apk";

	public static List<Left> left_list = null;

	public static boolean login_status = false;

}
