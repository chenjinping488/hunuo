package com.example.hunuo_apk;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductActivity extends BaseActivity {
	// 顶部
	ImageButton top_back;
	ImageView top_left;
	TextView top_text;
	ImageView top_right;
	// 中部

	LinearLayout product_business, product_brand, product_interactive, product_mall, product_mobile, product_android, product_ios, product_weixin;

	// 底部
	// LinearLayout refresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product);

		initView();
	}

	private void initView() {
		top_back = (ImageButton) this.findViewById(R.id.top_back);
		top_left = (ImageView) this.findViewById(R.id.top_left);
		top_text = (TextView) this.findViewById(R.id.top_text);
		top_right = (ImageView) this.findViewById(R.id.top_right);

		product_business = (LinearLayout) this.findViewById(R.id.product_business);
		product_brand = (LinearLayout) this.findViewById(R.id.product_brand);
		product_interactive = (LinearLayout) this.findViewById(R.id.product_interactive);
		product_mall = (LinearLayout) this.findViewById(R.id.product_mall);
		product_mobile = (LinearLayout) this.findViewById(R.id.product_mobile);
		product_android = (LinearLayout) this.findViewById(R.id.product_android);
		product_ios = (LinearLayout) this.findViewById(R.id.product_ios);
		product_weixin = (LinearLayout) this.findViewById(R.id.product_weixin);

		top_text.setText("产品中心");

		top_left.setVisibility(View.VISIBLE);
		top_right.setVisibility(View.VISIBLE);
		top_left.setOnClickListener(new ClickEvent());
		top_right.setOnClickListener(new ClickEvent());

		product_business.setOnClickListener(new ClickEvent());
		product_brand.setOnClickListener(new ClickEvent());
		product_interactive.setOnClickListener(new ClickEvent());
		product_mall.setOnClickListener(new ClickEvent());
		product_mobile.setOnClickListener(new ClickEvent());
		product_android.setOnClickListener(new ClickEvent());
		product_ios.setOnClickListener(new ClickEvent());
		product_weixin.setOnClickListener(new ClickEvent());
	}

	public class ClickEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.top_left:
				MainActivity main = (MainActivity) getParent();
				main.toggle();
				break;
			case R.id.top_right:
				// openActivity(null, null);
				break;
			case R.id.product_business:
				// openActivity(null, null);
				break;
			case R.id.product_brand:
				// openActivity(null, null);
				break;
			case R.id.product_interactive:
				// openActivity(null, null);
				break;
			case R.id.product_mall:
				// openActivity(null, null);
				break;
			case R.id.product_mobile:
				// openActivity(null, null);
				break;
			case R.id.product_android:
				// openActivity(null, null);
				break;
			case R.id.product_ios:
				// openActivity(null, null);
				break;
			case R.id.product_weixin:
				// openActivity(null, null);
				break;
			default:
				break;
			}
		}
	}

	/*
	 * private void load_error(Boolean control) { if(control) {
	 * refresh.setVisibility(View.GONE); } else {
	 * refresh.setVisibility(View.VISIBLE); } }
	 */

}
