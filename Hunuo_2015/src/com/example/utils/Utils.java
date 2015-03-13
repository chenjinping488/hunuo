package com.example.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

public class Utils {

	public static Bitmap getBitMBitmap(String urlpath) {
		Bitmap map = null;
		try {
			URL url = new URL(urlpath);
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStream in;
			in = conn.getInputStream();
			map = BitmapFactory.decodeStream(in);
			// TODO Auto-generated catch block
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static void storeInSD(Bitmap bitmap1) {
		File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "hunuo" + File.separator);
		if (!file.exists()) {
			file.mkdir();
		}
		File imageFile = new File(file, getFileName() + ".png");
		try {
			imageFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(imageFile);
			bitmap1.compress(CompressFormat.PNG, 50, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getFileName() {
		String rel = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date curDate = new Date(System.currentTimeMillis());
		rel = formatter.format(curDate);
		String fileNameRandom = rel;
		return fileNameRandom;
	}

	// 判断SD卡是否存在
	public static boolean sdCardExist() {
		return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	// 取SD卡的根路径
	public static String getSDPath() {
		File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "hunuo" + File.separator);
		if (!file.exists()) {
			file.mkdir();
		} else {
			File file2 = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "hunuo" + File.separator + "hunuo.apk");
			if (file2.exists()) {
				file2.delete();
			}
		}
		return Environment.getExternalStorageDirectory().getPath() + File.separator + "hunuo" + File.separator;
	}
}
