package com.example.hunuo_apk;

import net.tsz.afinal.annotation.view.ViewInject;

import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.http.HttpRequest;
import com.example.utils.Constants;

public class LoginActivity extends BaseActivity {

	@ViewInject(id = R.id.top_back)
	ImageButton top_back;

	@ViewInject(id = R.id.top_left)
	ImageView top_left;

	@ViewInject(id = R.id.top_text)
	TextView top_text;

	@ViewInject(id = R.id.top_right)
	ImageView top_right;

	@ViewInject(id = R.id.login_username)
	EditText login_username;

	@ViewInject(id = R.id.login_password)
	EditText login_password;

	@ViewInject(id = R.id.login_btn, click = "click")
	Button login_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		top_text.setText("用户登陆");
	}

	public void click(View v) {
		switch (v.getId()) {
		case R.id.login_btn:
			if (login_username.getText().toString().trim().equals("")) {
				if (login_password.getText().toString().trim().equals("")) {
					HttpRequest.getInstance(this, 0).login(login_username.getText().toString().trim(), login_password.getText().toString().trim());
				} else {
					showToast(this, "请输入密码");
				}
			} else {
				showToast(this, "请输入用户名");
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onHttpSuccess(Object t, int flag) {
		// TODO Auto-generated method stub
		super.onHttpSuccess(t, flag);
		System.out.println(t.toString());
	}

	// private void login_click() {
	//
	// RequestParams requestparams = new RequestParams();
	// requestparams.put("username", username);
	// requestparams.put("userpw", password);
	//
	// client.post(Constants.LOGIN_URL, requestparams, new
	// AsyncHttpResponseHandler() {
	// public void onSuccess(int statusCode, String content) {
	// System.out.println(statusCode + content);
	// if (statusCode == 200) {
	// if (content == "") {
	// BaseActivity.showToast(LoginActivity.this, "��½ʧ��");
	// } else if (content == "0") {
	// BaseActivity.showToast(LoginActivity.this, "��½ʧ��");
	// } else {
	// preferences.putString("name", username);
	// preferences.putString("password", password);
	// preferences.putString("id", content);
	// preferences.putInt("status", 1);
	// preferences.commit();
	//
	// Bundle bundle = new Bundle();
	// bundle.putString("id", content);
	//
	// LoginActivity.this.openActivity(MapActivity.class, bundle);
	// LoginActivity.this.finish();
	// }
	// }
	// };
	//
	// public void onFailure(Throwable error) {
	// BaseActivity.showToast(LoginActivity.this, "��½ʧ��");
	// };
	// });
	// }
	// }
}
