package com.example.hunuo_apk;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.OverlayManager;

public class MapActivity extends BaseActivity implements BaiduMap.OnMapClickListener {
	ImageButton top_back;
	ImageView top_left;
	TextView top_text;
	ImageView top_right;
	MapView baidu_mapview;
	BaiduMap mBaiduMap;
	// ͼƬ
	ImageView card_photo_1, card_photo_2, card_photo_3;

	Button card_btn;

	boolean isFirstLoc = true;

	LocationClient mLocClient;
	MyLocationListenner myListener = new MyLocationListenner();

	OverlayManager routeOverlay = null;
	LatLng startLatLng;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		initView();
	}

	private void initView() {
		top_back = (ImageButton) this.findViewById(R.id.top_back);
		top_left = (ImageView) this.findViewById(R.id.top_left);
		top_text = (TextView) this.findViewById(R.id.top_text);
		top_right = (ImageView) this.findViewById(R.id.top_right);

		baidu_mapview = (MapView) this.findViewById(R.id.baidu_mapview);
		card_photo_1 = (ImageView) this.findViewById(R.id.card_photo_1);
		card_photo_2 = (ImageView) this.findViewById(R.id.card_photo_2);
		card_photo_3 = (ImageView) this.findViewById(R.id.card_photo_3);

		card_btn = (Button) this.findViewById(R.id.card_btn);

		top_text.setText("�򿨵�½");

		top_back.setVisibility(View.VISIBLE);
		top_back.setOnClickListener(new ClickEvent());
		card_btn.setOnClickListener(new ClickEvent());

		mBaiduMap = baidu_mapview.getMap();
		mBaiduMap.setMyLocationEnabled(true);

		mLocClient = new LocationClient(getApplicationContext());
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setCoorType("bd09ll");
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	public class ClickEvent implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.top_left:
				MainActivity main = (MainActivity) getParent();
				main.toggle();
				break;
			case R.id.card_btn:
				card_click();
				break;
			default:
				break;
			}
		}
	}

	private void card_click() {

	}

	@Override
	protected void onResume() {
		baidu_mapview.onResume();
		super.onResume();
	}

	@Override
	protected void onPause() {
		baidu_mapview.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		mLocClient.stop();
		mBaiduMap.setMyLocationEnabled(false);
		baidu_mapview.onDestroy();
		baidu_mapview = null;
		super.onDestroy();
	}

	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {

		}

		@Override
		public void onReceivePoi(BDLocation location) {

		}
	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMapPoiClick(MapPoi arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
