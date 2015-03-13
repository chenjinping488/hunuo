package com.example.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.adapter.LeftmenuAdapter;
import com.example.entity.Left;
import com.example.utils.Constants;
import com.example.hunuo_apk.LoginActivity;
import com.example.hunuo_apk.MainActivity;
import com.example.hunuo_apk.MapActivity;
import com.example.hunuo_apk.R;

@SuppressLint("NewApi")
public class MenuFragment extends Fragment 
{
	ListView left_listview;
	LeftmenuAdapter adapter;
	List<Left> list= new ArrayList<Left>();
	int[] left_images = {R.drawable.left_menu_ico_1, R.drawable.left_menu_ico_2, R.drawable.left_menu_ico_3, R.drawable.left_menu_ico_4,
			 			 R.drawable.left_menu_ico_5, R.drawable.left_menu_ico_6, R.drawable.left_menu_ico_7, R.drawable.left_menu_ico_8,
			 			 R.drawable.left_menu_ico_9, R.drawable.left_menu_ico_10};
	String[] left_text = {"返回首页","网站建设","手机开发","微信营销","成功案例","产品服务","新闻中心","关于我们","用户登录","联系我们"};
	//String[] left_urls = {""};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View menuView = inflater.inflate(R.layout.left_menu, null);
		left_listview = (ListView)menuView.findViewById(R.id.left_listview);
		for(int i=0;i<left_text.length;i++)
		{
			Left left = new Left();
			left.setPic(left_images[i]);
			left.setName(left_text[i]);
			list.add(i, left);
		}
		
		adapter = new LeftmenuAdapter(getActivity(), list);
		left_listview.setAdapter(adapter);

		left_listview.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int postion, long arg3) 
			{
				switch (postion)
				{
					// 返回首页
					case 0:
						MainActivity mainActivity = (MainActivity) getActivity();
						mainActivity.toggle();
						MainActivity.tabHost.setCurrentTab(0);
						break;
					// 网站建设
					case 1:
						//Intent intent = new Intent(getActivity(), LoginActivity.class);
						//startActivity(intent);
						break;
					// 手机开发
					case 2:
						//Intent intent = new Intent(getActivity(), LoginActivity.class);
						//startActivity(intent);
						break;
					// 微信营销
					case 3:
						//Intent intent = new Intent(getActivity(), LoginActivity.class);
						//startActivity(intent);
						break;
					// 成功案例
					case 4:
						MainActivity mainActivity2 = (MainActivity) getActivity();
						mainActivity2.toggle();
						MainActivity.tabHost.setCurrentTab(2);
						break;
					// 产品服务
					case 5:
						MainActivity mainActivity3 = (MainActivity) getActivity();
						mainActivity3.toggle();
						MainActivity.tabHost.setCurrentTab(3);
						break;
					// 新闻中心
					case 6:
						//Intent intent = new Intent(getActivity(), LoginActivity.class);
						//startActivity(intent);
						break;
					// 关于我们
					case 7:
						MainActivity mainActivity1 = (MainActivity) getActivity();
						mainActivity1.toggle();
						MainActivity.tabHost.setCurrentTab(1);
						break;
					// 用户登录
					case 8:
						if(Constants.login_status)
						{
							Intent intent = new Intent(getActivity(), MapActivity.class);
							startActivity(intent);
						}
						else
						{
							Intent intent = new Intent(getActivity(), LoginActivity.class);
							startActivity(intent);
						}
						break;
					// 联系我们
					case 9:
						MainActivity mainActivity4 = (MainActivity) getActivity();
						mainActivity4.toggle();
						MainActivity.tabHost.setCurrentTab(4);
						break;
					default:
						break;
				}
			}
		});

		return menuView;
	}

	private ListView findViewById(int leftListview) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	private String getCode(String str) {
		if (Constants.left_list != null) {
			for (Left list : Constants.left_list) {
				if (str.equals(list.getName().toString())) {
					return list.getCode();
				}
			}
		}
		return null;
	}

}
