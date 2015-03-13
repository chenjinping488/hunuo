package com.example.hunuo_apk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adapter.BannerAdapter;
import com.example.entity.Banner;
import com.example.widget.PullToRefreshView;
import com.example.widget.PullToRefreshView.OnFooterRefreshListener;
import com.example.widget.PullToRefreshView.OnHeaderRefreshListener;

public class HomeActivity extends BaseActivity implements OnFooterRefreshListener, OnHeaderRefreshListener {
	ScheduledExecutorService scheduledExecutorService;
	ImageButton top_back;
	ImageView top_left;
	TextView top_text;
	ImageView top_right;
	ViewPager viewpager;
	ViewGroup viewgroup;
	LinearLayout home_website, home_phone, home_weixin, home_case, home_product, home_news;

	BannerAdapter adapter;
	int currentItem = 0;
	ImageView[] images;
	List<Banner> list = new ArrayList<Banner>();
	int[] list_image = { R.drawable.home_banner_image_1, R.drawable.home_banner_image_1, R.drawable.home_banner_image_1 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		initView();
	}

	@Override
	protected void onStop() {
		if (scheduledExecutorService != null) {
			scheduledExecutorService.shutdown();
		}
		super.onStop();
	}

	private void initBanner() {
		for (int i = 0; i < list_image.length; i++) {
			Banner banner = new Banner();
			banner.setPic(list_image[i]);
			list.add(i, banner);
		}

		int num = list.size();
		images = new ImageView[num];
		for (int i = 0; i < num; i++) {
			images[i] = new ImageView(this);
			LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(5, 5);
			layout.setMargins(10, 0, 0, 0);
			images[i].setLayoutParams(layout);
			if (i == 0) {
				images[i].setImageResource(R.drawable.home_banner_ico_2);
			} else {
				images[i].setImageResource(R.drawable.home_banner_ico_1);
			}
			viewgroup.addView(images[i]);
		}
		adapter = new BannerAdapter(HomeActivity.this, list);

		viewpager.setOnPageChangeListener(new pagerListener());
		viewpager.setAdapter(adapter);
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 8, TimeUnit.SECONDS);
	}

	private class ScrollTask implements Runnable {
		public void run() {
			synchronized (viewpager) {
				currentItem = (currentItem + 1) % images.length;
				handler.obtainMessage().sendToTarget();
			}
		}
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			viewpager.setCurrentItem(currentItem);
		};
	};

	private class pagerListener implements OnPageChangeListener {
		private int oldPosition = 0;

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int position) {
			currentItem = position;
			images[position].setImageResource(R.drawable.home_banner_ico_2);
			images[oldPosition].setImageResource(R.drawable.home_banner_ico_1);
			oldPosition = position;
		}
	}

	private void initView() {
		top_back = (ImageButton) this.findViewById(R.id.top_back);
		top_left = (ImageView) this.findViewById(R.id.top_left);
		top_text = (TextView) this.findViewById(R.id.top_text);
		top_right = (ImageView) this.findViewById(R.id.top_right);
		viewpager = (ViewPager) this.findViewById(R.id.viewpager);
		viewgroup = (ViewGroup) this.findViewById(R.id.viewgroup);
		home_website = (LinearLayout) this.findViewById(R.id.home_website);
		home_phone = (LinearLayout) this.findViewById(R.id.home_phone);
		home_weixin = (LinearLayout) this.findViewById(R.id.home_weixin);
		home_case = (LinearLayout) this.findViewById(R.id.home_case);
		home_product = (LinearLayout) this.findViewById(R.id.home_product);
		home_news = (LinearLayout) this.findViewById(R.id.home_news);

		top_text.setText("��ŵ�Ƽ�");

		top_left.setVisibility(View.VISIBLE);
		top_right.setVisibility(View.VISIBLE);
		top_left.setOnClickListener(new ClickEvent());
		top_right.setOnClickListener(new ClickEvent());
	}

	private void initDate() {
		/*
		 * AsyncHttpClient client = new AsyncHttpClient();
		 * client.setTimeout(5000); client.get(Constants.BANNER_URL, new
		 * AsyncHttpResponseHandler()// params, {
		 * 
		 * @Override public void onSuccess(int statusCode, Header[] headers,
		 * byte[] responseBody) {
		 * 
		 * }
		 * 
		 * @Override public void onFinish() {
		 * 
		 * } });
		 */
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
				// ;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {

	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {

	}

}
