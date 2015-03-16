package com.example.hunuo_apk;

import net.tsz.afinal.FinalActivity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.application.ActivityManager;
import com.example.http.HttpResponseImp;
import com.example.widget.LoadingDialog;

public class BaseActivity extends FinalActivity implements HttpResponseImp {
	private static final String TAG = "BaseActivity";

	private static AnimationDrawable animationdrawable = new AnimationDrawable();

	public Dialog dialog = null;

	/************************ Activity LifeCycle For Debug *******************/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, this.getClass().getSimpleName() + " onCreate() invoked!!");
		super.onCreate(savedInstanceState);
		ActivityManager.getInstance().addActivity(this);
	}

	@Override
	protected void onStart() {
		Log.d(TAG, this.getClass().getSimpleName() + " onStart() invoked!!");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, this.getClass().getSimpleName() + " onRestart() invoked!!");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, this.getClass().getSimpleName() + " onResume() invoked!!");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.d(TAG, this.getClass().getSimpleName() + " onPause() invoked!!");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, this.getClass().getSimpleName() + " onStop() invoked!!");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, this.getClass().getSimpleName() + " onDestroy() invoked!!");
		super.onDestroy();
	}

	/******************************** OpenActivity ***********************/

	protected void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	protected void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	protected void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	protected void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	public void openActivityForResult(Class<?> pClass, int requestCode, Bundle options) {
		Intent intent = new Intent(this, pClass);
		if (options != null) {
			intent.putExtras(options);
		}
		startActivityForResult(intent, requestCode);
	}

	public static void showToast(Context context, String str) {
		if (context != null && str != null) {
			Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
		}
	}

	public ProgressDialog showDialog(Context context) {
		ProgressDialog dialog = null;
		if (context != null) {
			dialog = ProgressDialog.show(this, "", "加载中..");
		}
		return dialog;
	}

	public void closeDialog(ProgressDialog dialog) {
		if (dialog != null) {
			if (animationdrawable.isRunning()) {
				animationdrawable.stop();
			}
			dialog.dismiss();
		}
	}

	public static Dialog loadingDialog(Context context, String msg) {
		LoadingDialog dialog = new LoadingDialog(context);
		dialog.setText(msg);
		return dialog;
	}

	@Override
	public void onHttpStart(int flag) {
		// TODO Auto-generated method stub
		if (flag != -1) {
			dialog = loadingDialog(this, getString(R.string.loading));
			dialog.show();
		}
	}

	@Override
	public void onHttpSuccess(Object t, int flag) {
		// TODO Auto-generated method stub
		if (flag != -1) {
			dialog.dismiss();
		}
	}

	@Override
	public void onHttpLoading(long count, long current, int flag) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHttpFailure(Throwable t, int errorNo, String strMsg, int flag) {
		// TODO Auto-generated method stub
		if (flag != -1) {
			dialog.dismiss();
		}
	}
}
