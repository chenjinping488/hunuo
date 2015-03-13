package com.example.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Left;
import com.example.hunuo_apk.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LeftmenuAdapter extends BaseAdapter {
	private List<Left> list = new ArrayList<Left>();
	private Context context;

	public LeftmenuAdapter(Context context, List<Left> list) {
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.left_menu_item, null);
			holder.left_menu_image = (ImageView) convertView.findViewById(R.id.left_menu_image);
			holder.left_menu_text = (TextView) convertView.findViewById(R.id.left_menu_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.left_menu_image.setBackgroundResource(list.get(position).getPic());
		holder.left_menu_text.setText(list.get(position).getName());
		return convertView;
	}

	class ViewHolder {
		ImageView left_menu_image;
		TextView left_menu_text;
	}

}
