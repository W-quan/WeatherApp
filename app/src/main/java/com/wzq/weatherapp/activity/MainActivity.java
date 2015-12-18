package com.wzq.weatherapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wzq.weatherapp.MyApplication;
import com.wzq.weatherapp.R;
import com.wzq.weatherapp.bean.WeatherInfo;
import com.wzq.weatherapp.util.MyUtil;
import com.wzq.weatherapp.view.DrawNumView;
import com.wzq.weatherapp.view.HumidityView;
import com.wzq.weatherapp.view.MaxMinTempView;
import com.wzq.weatherapp.view.TempView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
	private SharedPreferences setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MyApplication.addActivity(this);

		initUI();
	}


	private void initUI(){
		//初始化view的值
		WeatherInfo.HeWeather heWeather = MyApplication.getWeatherInfo()
				.getHeWeathers().get(0);

		TextView cityName = (TextView) findViewById(R.id.cityName_text);
		cityName.setText(heWeather.getBasic().getCity());

		TextView date = (TextView) findViewById(R.id.date_text);
		TextView time = (TextView) findViewById(R.id.time_text);
		date.setText(heWeather.getBasic().getUpdate().getLoc().split(" ")[0]);
		time.setText(heWeather.getBasic().getUpdate().getLoc().split(" ")[1]);

		ImageView weatherImg = (ImageView) findViewById(R.id.weather_Img);
		weatherImg.setImageResource(
				new MyUtil().setWeatherIcon(heWeather.getNow().getCond().getCode()));

		HumidityView humidityView = (HumidityView) findViewById(R.id.humidity_view);
		humidityView.setHumidity(heWeather.getNow().getHum());

		MaxMinTempView minmaxTempView = (MaxMinTempView) findViewById(R.id.minmaxTemp_view);
		minmaxTempView.setTemp(heWeather.getDaily_forecast().get(0).getTmp().getMax(),
				heWeather.getDaily_forecast().get(0).getTmp().getMin());

		TempView tempView = (TempView) findViewById(R.id.temp_View);
		tempView.setTemp(heWeather.getNow().getTmp());

		DrawNumView pm25Show = (DrawNumView) findViewById(R.id.pm25_Show);
		pm25Show.setPm25Value(heWeather.getAqi().getCity().getPm25());

		TextView windValueText = (TextView) findViewById(R.id.wind_value_text);
		String s = "风力" + heWeather.getNow().getWind().getSc();
		windValueText.setText(s);

		//滑动监听
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_main_Layout);
		linearLayout.setOnTouchListener(this);

		ImageButton menuButton = (ImageButton) findViewById(R.id.menuButton);
		menuButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, PreferenceActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			}
		});

		//动画效果
		animationUI();
	}

	private void animationUI(){
		Animation fencheRotate = AnimationUtils.loadAnimation(this, R.anim.fengche_rotate);
		fencheRotate.setInterpolator(new LinearInterpolator());

		ImageView windfenche = (ImageView) findViewById(R.id.wind_fenche);
		windfenche.startAnimation(fencheRotate);

		Animation translatein = AnimationUtils.loadAnimation(this, R.anim.weather_icon_down);
		ImageView weatherImg = (ImageView) findViewById(R.id.weather_Img);
		weatherImg.startAnimation(translatein);

		translatein = AnimationUtils.loadAnimation(this, R.anim.main_activity_text_translate_right);

		TextView cityName = (TextView) findViewById(R.id.cityName_text);
		TextView weatherText = (TextView) findViewById(R.id.weather_text);
		TextView humidityText = (TextView) findViewById(R.id.humidity_text);
		TextView minmaxTempText = (TextView) findViewById(R.id.minmaxTemp_text);
		TextView tempText = (TextView) findViewById(R.id.temp_text);
		TextView pm25Text = (TextView) findViewById(R.id.pm25_text);
		TextView windText = (TextView) findViewById(R.id.wind_text);

		cityName.startAnimation(translatein);
		weatherText.startAnimation(translatein);
		humidityText.startAnimation(translatein);
		minmaxTempText.startAnimation(translatein);
		tempText.startAnimation(translatein);
		pm25Text.startAnimation(translatein);
		windText.startAnimation(translatein);

		TempView tempView = (TempView) findViewById(R.id.temp_View);
		DrawNumView pm25Show = (DrawNumView) findViewById(R.id.pm25_Show);
		ImageButton menuButton = (ImageButton) findViewById(R.id.menuButton);
		HumidityView humidityView = (HumidityView) findViewById(R.id.humidity_view);

		translatein = AnimationUtils.loadAnimation(this, R.anim.main_activity_translate_lefe);
		tempView.startAnimation(translatein);
		pm25Show.startAnimation(translatein);
		menuButton.startAnimation(translatein);
		humidityView.startAnimation(translatein);

		TextView date = (TextView) findViewById(R.id.date_text);
		TextView time = (TextView) findViewById(R.id.time_text);
		translatein = AnimationUtils.loadAnimation(this, R.anim.date_time_translate_down);
		date.startAnimation(translatein);
		time.startAnimation(translatein);

	}

	@Override
	protected void onResume() {
		super.onResume();
//		if (!cityName.getText().equals(setting.getString("localCity", "广州"))) {
//			Intent intent = new Intent(MainActivity.this, UpdatedActivity.class);
//			startActivity(intent);
//			finish();
//		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		MyApplication.removeActivity(this);
	}

	private float downY = 0;
	private float downX = 0;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		v.performClick();
		int actionType = event.getActionMasked();
		switch (actionType) {
			case MotionEvent.ACTION_DOWN:
				downY = event.getY();
				downX = event.getX();
				float upY;
				float upX;
				return true;
			case MotionEvent.ACTION_UP:
				upY = event.getY();
				upX = event.getX();
				if (downY - upY > 100) {
					Intent intent = new Intent(MainActivity.this, Next7DayWeatherActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.in_from_down, R.anim.out_to_up);
				}
				return true;
		}
		return false;
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
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
