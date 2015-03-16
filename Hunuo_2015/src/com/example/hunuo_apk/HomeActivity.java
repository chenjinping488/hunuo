package com.example.hunuo_apk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.tsz.afinal.annotation.view.ViewInject;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adapter.BannerAdapter;
import com.example.entity.Banner;
import com.example.http.HttpRequest;
import com.example.utils.ParserUtils;
import com.example.widget.PullToRefreshView;
import com.example.widget.PullToRefreshView.OnFooterRefreshListener;
import com.example.widget.PullToRefreshView.OnHeaderRefreshListener;

public class HomeActivity extends BaseActivity implements OnFooterRefreshListener, OnHeaderRefreshListener {

	ScheduledExecutorService scheduledExecutorService;

	@ViewInject(id = R.id.top_back, click = "clickEvent")
	ImageButton top_back;

	@ViewInject(id = R.id.top_left, click = "clickEvent")
	ImageView top_left;

	@ViewInject(id = R.id.top_text)
	TextView top_text;

	@ViewInject(id = R.id.top_right)
	ImageView top_right;

	@ViewInject(id = R.id.viewpager)
	ViewPager viewpager;

	@ViewInject(id = R.id.viewgroup)
	ViewGroup viewgroup;

	@ViewInject(id = R.id.home_website, click = "clickEvent")
	View home_website;

	@ViewInject(id = R.id.home_phone, click = "clickEvent")
	View home_phone;

	@ViewInject(id = R.id.home_case, click = "clickEvent")
	View home_case;

	@ViewInject(id = R.id.home_weixin, click = "clickEvent")
	View home_weixin;

	@ViewInject(id = R.id.home_product, click = "clickEvent")
	View home_product;

	@ViewInject(id = R.id.home_news, click = "clickEvent")
	View home_news;

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

		top_text.setText("");
		top_back.setVisibility(View.GONE);
		top_right.setVisibility(View.VISIBLE);

		HttpRequest.getInstance(this, 0).getIndex();
	}

	@Override
	protected void onStop() {
		if (scheduledExecutorService != null) {
			scheduledExecutorService.shutdown();
		}
		super.onStop();
	}

	@Override
	public void onHttpSuccess(Object t, int flag) {
		// TODO Auto-generated method stub
		super.onHttpSuccess(t, flag);
		System.out.println(t.toString());
		try {
			List<Banner> banners = ParserUtils.parserBanner(t.toString());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
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

	public void clickEvent(View v) {
		switch (v.getId()) {
		case R.id.top_back:

			break;

		default:
			break;
		}

	}

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {

	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {

	}

}
