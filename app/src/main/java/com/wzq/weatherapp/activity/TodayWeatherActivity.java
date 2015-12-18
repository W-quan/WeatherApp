package com.wzq.weatherapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wzq.weatherapp.MyApplication;
import com.wzq.weatherapp.R;

public class TodayWeatherActivity extends Activity implements View.OnTouchListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.today_weather_activity);

		MyApplication.addActivity(this);

		RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.today_chart_layout);
		rLayout.setOnTouchListener(this);
	}

	private float downY = 0, upY = 0;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		v.performClick();
		int actionType = event.getActionMasked();
		switch (actionType) {
			case MotionEvent.ACTION_DOWN:
				downY = event.getY();
				upY = downY;
				return true;
			case MotionEvent.ACTION_UP:
				upY = event.getY();
				if (upY - downY> 100) {
					finish();
					overridePendingTransition(R.anim.in_from_up, R.anim.out_to_down);
				}
				return true;
		}
		return false;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		overridePendingTransition(R.anim.in_from_up, R.anim.out_to_down);
	}

	private long exitTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				MyApplication.finishAll();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		MyApplication.removeActivity(this);
	}
}
