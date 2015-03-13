package com.example.hunuo_apk;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutActivity extends BaseActivity {
	ImageButton top_back;
	ImageView top_left;
	TextView top_text;
	ImageView top_right;
	WebView about_webview;
	LinearLayout refresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		initView();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initView() {
		top_back = (ImageButton) this.findViewById(R.id.top_back);
		top_left = (ImageView) this.findViewById(R.id.top_left);
		top_text = (TextView) this.findViewById(R.id.top_text);
		top_right = (ImageView) this.findViewById(R.id.top_right);

		about_webview = (WebView) this.findViewById(R.id.about_webview);

		refresh = (LinearLayout) this.findViewById(R.id.refresh);

		about_webview.getSettings().setJavaScriptEnabled(true);
		WebSettings webSettings = about_webview.getSettings();
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		about_webview.setWebViewClient(new WebClient());

		top_text.setText("��������");

		top_left.setVisibility(View.VISIBLE);
		top_right.setVisibility(View.VISIBLE);
		top_left.setOnClickListener(new ClickEvent());
		top_right.setOnClickListener(new ClickEvent());
		refresh.setOnClickListener(new ClickEvent());
	}

	public class ClickEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.top_left:
				MainActivity main = (MainActivity) getParent();
				main.toggle();
				break;
			case R.id.refresh:
				load_web();
				load_error(true);
				break;
			default:
				break;
			}
		}
	}

	private class WebClient extends WebViewClient {
		@Override
		public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
			BaseActivity.showToast(AboutActivity.this, "����ʧ��");
			load_error(false);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			BaseActivity.showToast(AboutActivity.this, "����ʧ��");
			load_error(false);
		}
	}

	private void load_web() {
		about_webview.loadUrl("http://www.baidu.com/");
		// about_webview.loadUrl(Constants.ORDER_DETAIL_URL);
	}

	private void load_error(Boolean control) {
		if (control) {
			about_webview.setVisibility(View.VISIBLE);
			refresh.setVisibility(View.GONE);
		} else {
			about_webview.setVisibility(View.GONE);
			refresh.setVisibility(View.VISIBLE);
		}
	}

}
