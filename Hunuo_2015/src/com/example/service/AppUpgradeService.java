package com.example.service;

import java.io.File;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;

import com.example.hunuo_apk.MainActivity;
import com.example.hunuo_apk.R;
import com.example.utils.Constants;
import com.example.utils.Utils;

public class AppUpgradeService extends Service {

	/** ֪ͨ������ */
	private NotificationManager updateNotificationManager = null;
	private Notification updateNotification = null;

	// ֪ͨ����תIntent
	private Intent updateIntent = null;

	private RemoteViews views;

	private int precent = 0;

	private Handler handler = new Handler() {

		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case 2:
				views.setTextViewText(R.id.tvProcess, precent + "%");
				views.setProgressBar(R.id.update_progress, 100, precent, false);
				updateNotification.contentView = views;
				updateNotificationManager.notify(0, updateNotification);
				if (precent == 100) {
					updateNotificationManager.cancel(0);
				}
				break;
			case 3:
				updateNotificationManager.cancel(0);
				break;
			default:
				break;
			}
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		if (Utils.sdCardExist()) {
			createNotification();
		} else {
			return super.onStartCommand(intent, flags, startId);
		}
		return super.onStartCommand(intent, flags, startId);
	}

	private void download() {
		FinalHttp finalHttp = new FinalHttp();
		finalHttp.configTimeout(10000);
		finalHttp.download(Constants.DOWNLOAD_URL, null, Utils.getSDPath() + "hunuo.apk", true, new AjaxCallBack<File>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
			}

			@Override
			public void onSuccess(File t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				instanll(t);
				precent = 0;
				updateNotificationManager.cancel(0);
				// ֹͣ����ǰ�ķ���
				stopSelf();
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				handler.sendEmptyMessage(3);
				
			}

			@Override
			public void onLoading(long count, long current) {
				// TODO Auto-generated method stub
				super.onLoading(count, current);
				precent = Integer.parseInt(String.format("%.0f", ((double) current / (double) count) * 100));
				handler.sendEmptyMessage(2);
			}
		});
	}

	/**
	 * 
	 * @Description: ����֪ͨ [url=home.php?mod=space&uid=309376]@return[/url] void
	 */
	public void createNotification() {

		this.updateNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		this.updateNotification = new Notification();

		// �������ع����У����֪ͨ�����ص�������
		updateIntent = new Intent(this, MainActivity.class);
		PendingIntent.getActivity(this, 0, updateIntent, 0);
		// ����֪ͨ����ʾ����
		updateNotification.icon = R.drawable.ic_launcher;
		updateNotification.tickerText = "��ʼ���ظ���";
		download();
		// ���������������ؽ�����ʾ��views
		views = new RemoteViews(getPackageName(), R.layout.update);
		updateNotification.contentView = views;
		// ����֪ͨ
		updateNotificationManager.notify(0, updateNotification);
	}

	// ��װ���غ��apk�ļ�
	private void instanll(File file) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		startActivity(intent);
	}
}
