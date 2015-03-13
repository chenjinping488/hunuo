package com.example.hunuo_apk;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CasesActivity extends BaseActivity {
	ImageButton top_back;
	ImageView top_left;
	TextView top_text;
	ImageView top_right;

	LinearLayout refresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cases);
		initView();
	}

	private void initView() {
		top_back = (ImageButton) this.findViewById(R.id.top_back);
		top_left = (ImageView) this.findViewById(R.id.top_left);
		top_text = (TextView) this.findViewById(R.id.top_text);
		top_right = (ImageView) this.findViewById(R.id.top_right);

		refresh = (LinearLayout) this.findViewById(R.id.refresh);

		top_text.setText("�ɹ�����");

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
				// load_web();
				// load_error(true);
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
