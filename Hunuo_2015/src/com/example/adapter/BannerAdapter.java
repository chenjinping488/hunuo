package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.entity.Banner;
import com.example.hunuo_apk.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BannerAdapter extends PagerAdapter {
	private View rowView;
	private Context context;
	private Map<Integer, View> rowViews = new HashMap<Integer, View>();

	List<Banner> list = new ArrayList<Banner>();

	protected ImageLoader imageLoader = ImageLoader.getInstance();

	public BannerAdapter(Context context, List<Banner> list) {
		this.context = context;
		this.list = list;

	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public void finishUpdate(View arg0) {

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		BannerHolder holder = null;
		rowView = rowViews.get(arg0);
		if (rowView == null) {
			rowView = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
			holder = new BannerHolder(rowView);
			rowView.setTag(holder);
		} else {
			holder = (BannerHolder) rowView.getTag();
		}
		holder.getImageView().setImageResource(list.get(arg1).getPic());
		// imageLoader.displayImage(list.get(arg1).getPic(),
		// holder.getImageView(), options);

		rowViews.put(arg1, rowView);
		((ViewPager) arg0).addView(rowView, 0);

		// holder.imageview.setOnClickListener(new PagerClickListener());
		return rowView;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {

	}

	private class BannerHolder {
		private View view;
		private ImageView imageview;

		private BannerHolder(View v) {
			view = v;
		}

		ImageView getImageView() {
			if (imageview == null) {
				imageview = (ImageView) view.findViewById(R.id.home_banner);
			}
			return imageview;
		}
	}
}
