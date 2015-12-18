package com.wzq.weatherapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.google.gson.Gson;
import com.wzq.weatherapp.bean.WeatherInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzq on 15-12-12.
 */
public class MyApplication extends Application {
    private static Context context;
    private static WeatherInfo weatherInfo;     //保存的天气信息

    public static List<Activity> activities = new ArrayList<Activity>();   //管理app中的activity

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        ApiStoreSDK.init(this, "734f90e3eacae435696adab4e03ae3da");
    }

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity: activities){
            if (! activity.isFinishing()){
                activity.finish();
            }
        }
    }

    public static Context getContext() {
        return context;
    }

    public static void setWeatherInfo(String weatherInfo) {
        Gson gson = new Gson();
        weatherInfo = weatherInfo.replaceFirst("HeWeather data service 3.0", "heWeathers");
        MyApplication.weatherInfo = gson.fromJson(weatherInfo, WeatherInfo.class);
    }

    public static WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }
}
