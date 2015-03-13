package com.example.hunuo_apk;

import android.app.LocalActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

import com.example.application.ActivityManager;
import com.example.fragment.MenuFragment;
import com.example.service.AppUpgradeService;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;

@SuppressWarnings("deprecation")
public class MainActivity extends SlidingFragmentActivity {
	public static TabHost tabHost;
	public FragmentTransaction fraTra = null;
	public MenuFragment menuFra = null;
	LocalActivityManager lam;

	// �ײ��˵�����ť
	private ImageView guide_home, guide_about, guide_case, guide_product, guide_contact;

	SharedPreferences preferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setBehindContentView(R.layout.left_menu); // ���ò˵�
		init(savedInstanceState);

		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}

	private void init(Bundle savedInstanceState) {
		fraTra = this.getSupportFragmentManager().beginTransaction();
		menuFra = new com.example.fragment.MenuFragment();
		fraTra.replace(R.id.left_menu_layout, menuFra);
		fraTra.commit();

		SlidingMenu sm = getSlidingMenu(); // �����˵�
		sm.setShadowWidth(15); // ��Ӱ���
		sm.setBehindOffset(100); // �˵���߿�ľ���
		sm.setShadowDrawable(R.drawable.shadow); // �����˵�����
		sm.setFadeDegree(0.35f); // ɫ��
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN); // ��Ե�����˵�

		tabHost = (TabHost) findViewById(android.R.id.tabhost);

		lam = new LocalActivityManager(MainActivity.this, false);
		lam.dispatchCreate(savedInstanceState);
		tabHost.setup(lam);

		preferences = getSharedPreferences("user", Context.MODE_PRIVATE);

		/*
		 * int flag = preferences.getInt("status", 0); if (flag == 1) {
		 * Constants.login_status = true; } else { Constants.login_status =
		 * false; }
		 */

		initView();
		initData();
	}

	private void initData() {

	}

	private void initView() {
		tabHost.addTab(tabHost.newTabSpec("guide_home").setIndicator("guide_home").setContent(new Intent(this, HomeActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("guide_about").setIndicator("guide_about").setContent(new Intent(this, AboutActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("guide_case").setIndicator("guide_case").setContent(new Intent(this, CasesActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("guide_product").setIndicator("guide_product").setContent(new Intent(this, ProductActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("guide_contact").setIndicator("guide_contact").setContent(new Intent(this, ContactActivity.class)));

		guide_home = (ImageView) findViewById(R.id.guide_home);
		guide_about = (ImageView) findViewById(R.id.guide_about);
		guide_case = (ImageView) findViewById(R.id.guide_case);
		guide_product = (ImageView) findViewById(R.id.guide_product);
		guide_contact = (ImageView) findViewById(R.id.guide_contact);

		guide_home.setOnClickListener(new ClickEvent());
		guide_about.setOnClickListener(new ClickEvent());
		guide_case.setOnClickListener(new ClickEvent());
		guide_product.setOnClickListener(new ClickEvent());
		guide_contact.setOnClickListener(new ClickEvent());

		tabHost.setOnTabChangedListener(new MyOnTabChangeListener());
	}

	private class ClickEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.guide_home:
				tabHost.setCurrentTabByTag("guide_home");
				break;
			case R.id.guide_about:
				tabHost.setCurrentTabByTag("guide_about");
				break;
			case R.id.guide_case:
				tabHost.setCurrentTabByTag("guide_case");
				break;
			case R.id.guide_product:
				tabHost.setCurrentTabByTag("guide_product");
				break;
			case R.id.guide_contact:
				tabHost.setCurrentTabByTag("guide_contact");
				break;
			default:
				break;
			}
		}
	}

	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				// Object mHelperUtils;
				Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				Intent exit = new Intent(Intent.ACTION_MAIN);
				exit.addCategory(Intent.CATEGORY_HOME);
				exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(exit);
				ActivityManager.getInstance().exit();
				// System.exit(0);
				this.finish();
			}
			return true;
		}
		// ����ִ�и������������¼�
		return super.onKeyDown(keyCode, event);
	}

	private class MyOnTabChangeListener implements OnTabChangeListener {
		@Override
		public void onTabChanged(String tabId) {
			if (tabId == "guide_home") {
				guide_home.setImageResource(R.drawable.foot_menu_button_1_2);
				guide_about.setImageResource(R.drawable.foot_menu_button_2_1);
				guide_case.setImageResource(R.drawable.foot_menu_button_3_1);
				guide_product.setImageResource(R.drawable.foot_menu_button_4_1);
				guide_contact.setImageResource(R.drawable.foot_menu_button_5_1);
			} else if (tabId == "guide_about") {
				guide_home.setImageResource(R.drawable.foot_menu_button_1_1);
				guide_about.setImageResource(R.drawable.foot_menu_button_2_2);
				guide_case.setImageResource(R.drawable.foot_menu_button_3_1);
				guide_product.setImageResource(R.drawable.foot_menu_button_4_1);
				guide_contact.setImageResource(R.drawable.foot_menu_button_5_1);
			} else if (tabId == "guide_case") {
				guide_home.setImageResource(R.drawable.foot_menu_button_1_1);
				guide_about.setImageResource(R.drawable.foot_menu_button_2_1);
				guide_case.setImageResource(R.drawable.foot_menu_button_3_2);
				guide_product.setImageResource(R.drawable.foot_menu_button_4_1);
				guide_contact.setImageResource(R.drawable.foot_menu_button_5_1);
			} else if (tabId == "guide_product") {
				guide_home.setImageResource(R.drawable.foot_menu_button_1_1);
				guide_about.setImageResource(R.drawable.foot_menu_button_2_1);
				guide_case.setImageResource(R.drawable.foot_menu_button_3_1);
				guide_product.setImageResource(R.drawable.foot_menu_button_4_2);
				guide_contact.setImageResource(R.drawable.foot_menu_button_5_1);
			} else if (tabId == "guide_contact") {
				guide_home.setImageResource(R.drawable.foot_menu_button_1_1);
				guide_about.setImageResource(R.drawable.foot_menu_button_2_1);
				guide_case.setImageResource(R.drawable.foot_menu_button_3_1);
				guide_product.setImageResource(R.drawable.foot_menu_button_4_1);
				guide_contact.setImageResource(R.drawable.foot_menu_button_5_2);
			}
		}
	}

	@Override
	protected void onPause() {
		lam.dispatchPause(isFinishing());
		super.onPause();
	}

	@Override
	protected void onResume() {
		// ©���@��һ�����e
		lam.dispatchResume();
		super.onResume();
	}

}
