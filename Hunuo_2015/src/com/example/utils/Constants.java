package com.example.utils;

import java.util.List;

import com.example.entity.Left;

public class Constants {

	/******************* URL *********************/
	public static String HOST_URL = "http://www.newivf.org/";
	// public static String APP_URL = "http://hunuoapp.gz1.hostadm.net/";
	public static String APP_URL = "http://192.168.0.132:8002/";

	public final static String BANNER_URL = HOST_URL + "/app/GetIndex.aspx";

	public final static String Top_URL = HOST_URL + "/app/GetTopmenu.aspx";

	public final static String VIDEO_URL = HOST_URL + "/app/GetVideo.aspx";

	public final static String REGISTER_URL = HOST_URL + "/app/GetRegister.aspx";

	public final static String DOCTOR_URL = HOST_URL + "/app/GetDoctor.aspx";

	public final static String LOGIN_URL = APP_URL + "/login.aspx";

	public final static String DOWNLOAD_URL = "http://www.17cycle.cn/app/bike.apk";

	/******************* ȫ�־�̬���� --�������� *********************/

	public static List<Left> left_list = null;

	public static boolean login_status = false;

}
