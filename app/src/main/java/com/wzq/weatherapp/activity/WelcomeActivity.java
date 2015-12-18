package com.wzq.weatherapp.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.wzq.weatherapp.MyApplication;
import com.wzq.weatherapp.R;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
	private boolean netConnected = true;	//网络状态

	private LocationClient mLocationClient = null;
	private BDLocationListener myListener = new MyLocationListener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);

		mLocationClient = new LocationClient(getApplicationContext());  //声明LocationClient类
		mLocationClient.registerLocationListener( myListener );    //注册监听函数
		initLocation();

		AlphaAnimation alpha = new AlphaAnimation((float) 0.1, 1);
		alpha.setDuration(2000);
		ImageView iv = (ImageView)findViewById(R.id.welcome_Img);
		iv.startAnimation(alpha);
		alpha.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				if (!isNetworkConnected()){
					Toast.makeText(MyApplication.getContext(), "网络不可用",
							Toast.LENGTH_LONG).show();
					netConnected = false;
				}
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
				if (!netConnected){
					finish();
					return;
				}
				Toast.makeText(MyApplication.getContext(), "正在获取天气信息",
						Toast.LENGTH_SHORT).show();
				mLocationClient.start();
			}
		});
	}

	Handler handler = new Handler(){
		int RequestNetSuccess = 1;
		int RequestNetError = 2;
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == RequestNetSuccess) {
				toMainActivity();
			}
			if (msg.what == RequestNetError) {
				finish();
			}
		}

	};

	private void toMainActivity() {
		Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	//检测网络是否可用
	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	private void requestWeather(String city) {
//		String[] s = city.split("市");
//		city = s[0];

		Parameters para = new Parameters();

		para.put("city", "guangzhou");
		ApiStoreSDK.execute("http://apis.baidu.com/heweather/weather/free",
				ApiStoreSDK.GET,
				para,
				new ApiCallBack() {

					@Override
					public void onSuccess(int status, String responseString) {
						Log.i("sdkdemo", "onSuccess");
						MyApplication.setWeatherInfo(responseString);
						handler.sendEmptyMessage(1);
					}

					@Override
					public void onComplete() {
						Log.i("sdkdemo", "onComplete");
					}

					@Override
					public void onError(int status, String responseString, Exception e) {
						Log.i("sdkdemo", "onError, status: " + status);
						Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
						Toast.makeText(MyApplication.getContext(), responseString,
								Toast.LENGTH_LONG).show();
						handler.sendEmptyMessage(2);
					}

				});
	}
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			//Receive Location
			StringBuilder sb = new StringBuilder(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());// 单位：公里每小时
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\nheight : ");
				sb.append(location.getAltitude());// 单位：米
				sb.append("\ndirection : ");
				sb.append(location.getDirection());// 单位度
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append("\ndescribe : ");
				sb.append("gps定位成功");

			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				//运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
				sb.append("\ndescribe : ");
				sb.append("网络定位成功");
			} else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
				sb.append("\ndescribe : ");
				sb.append("离线定位成功，离线定位结果也是有效的");
			} else if (location.getLocType() == BDLocation.TypeServerError) {
				sb.append("\ndescribe : ");
				sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
			} else if (location.getLocType() == BDLocation.TypeNetWorkException) {
				sb.append("\ndescribe : ");
				sb.append("网络不同导致定位失败，请检查网络是否通畅");
			} else if (location.getLocType() == BDLocation.TypeCriteriaException) {
				sb.append("\ndescribe : ");
				sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
			}
			sb.append("\nlocationdescribe : ");
			sb.append(location.getLocationDescribe());// 位置语义化信息
			List<Poi> list = location.getPoiList();// POI数据
			if (list != null) {
				sb.append("\npoilist size = : ");
				sb.append(list.size());
				for (Poi p : list) {
					sb.append("\npoi= : ");
					sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
				}
			}
			Log.i("BaiduLocationApiDem", sb.toString());
			requestWeather(location.getAddress().city);
		}
	}
	private void initLocation(){
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving
		);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
		option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(false);//可选，默认false,设置是否使用gps
		option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIsNeedLocationDescribe(false);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
		option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
		option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
		option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
		option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
		mLocationClient.setLocOption(option);
	}
}
