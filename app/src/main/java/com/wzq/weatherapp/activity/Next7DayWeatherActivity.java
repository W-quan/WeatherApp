package com.wzq.weatherapp.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wzq.weatherapp.MyApplication;
import com.wzq.weatherapp.R;
import com.wzq.weatherapp.bean.WeatherInfo;
import com.wzq.weatherapp.util.MyUtil;

import java.util.Calendar;
import java.util.Date;

public class Next7DayWeatherActivity extends Activity implements View.OnTouchListener {
    private WeatherInfo.HeWeather heWeather;

    private TextView[][] tempText;
    private ImageView[] weatherImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_7_day_weather_activity);

        MyApplication.addActivity(this);

        heWeather = MyApplication.getWeatherInfo().getHeWeathers().get(0);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.next_day_weather_layout);
        linearLayout.setOnTouchListener(this);

        tempText = new TextView[3][6];
        weatherImg = new ImageView[6];

        //获取今天是星期几
        toDay();

        int tempTextID[][] = {{R.id.x1y1, R.id.x1y2, R.id.x1y3, R.id.x1y4, R.id.x1y5, R.id.x1y6},
                {R.id.x2y1, R.id.x2y2, R.id.x2y3, R.id.x2y4, R.id.x2y5, R.id.x2y6},
                {R.id.x3y1, R.id.x3y2, R.id.x3y3, R.id.x3y4, R.id.x3y5, R.id.x3y6}};

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ITC Avant Garde Gothic LT Extra Light.ttf");
        Animation translate = AnimationUtils.loadAnimation(this, R.anim.week_translate_left);
        Animation translate1 = AnimationUtils.loadAnimation(this, R.anim.min_temp_translate_left);
        Animation translate2 = AnimationUtils.loadAnimation(this, R.anim.max_temp_translate_right);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                tempText[i][j] = (TextView) findViewById(tempTextID[i][j]);
                tempText[i][j].setTypeface(typeface);
                if (i == 0) {
                    setDay(i, j);
                    tempText[i][j].startAnimation(translate);
                } else if (i == 1) {
                    setMinTemp(i, j);
                    tempText[i][j].startAnimation(translate1);
                } else if (i == 2) {
                    setMaxTemp(i, j);
                    tempText[i][j].startAnimation(translate2);
                }
            }
        }

        int weaherImgID[] = {R.id.x4y1, R.id.x4y2, R.id.x4y3, R.id.x4y4, R.id.x4y5, R.id.x4y6};
        Animation translate3 = AnimationUtils.loadAnimation(this, R.anim.weather_img_translate_right);
        for (int i = 0; i < 6; i++) {
            weatherImg[i] = (ImageView) findViewById(weaherImgID[i]);
            setWeatherImg(i);
            weatherImg[i].startAnimation(translate3);
        }

    }

    private void setWeatherImg(int i) {
        MyUtil g = new MyUtil();
        switch (i) {
            case 0:
                weatherImg[i].setImageResource( g.setWeatherIcon(
                                heWeather.getDaily_forecast().get(1).getCond().getCode_d()));
                break;
            case 1:
                weatherImg[i].setImageResource( g.setWeatherIcon(
                        heWeather.getDaily_forecast().get(2).getCond().getCode_d()));
                break;
            case 2:
                weatherImg[i].setImageResource( g.setWeatherIcon(
                        heWeather.getDaily_forecast().get(3).getCond().getCode_d()));
                break;
            case 3:
                weatherImg[i].setImageResource( g.setWeatherIcon(
                        heWeather.getDaily_forecast().get(4).getCond().getCode_d()));
                break;
            case 4:
                weatherImg[i].setImageResource( g.setWeatherIcon(
                        heWeather.getDaily_forecast().get(5).getCond().getCode_d()));
                break;
            case 5:
                weatherImg[i].setImageResource( g.setWeatherIcon(
                        heWeather.getDaily_forecast().get(6).getCond().getCode_d()));
                break;
            default:
                break;
        }
    }

    private String day[] = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private int a = 0;

    private void toDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        a = cal.get(Calendar.DAY_OF_WEEK);
    }

    private void setDay(int i, int j) {
        tempText[i][j].setText(day[a - 1]);
        if (a > 6) {
            a = 0;
        }
        a++;
    }

    private void setMinTemp(int i, int j) {
        switch (j) {
            case 0:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(1).getTmp().getMin() + "°");
                break;
            case 1:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(2).getTmp().getMin() + "°");
                break;
            case 2:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(3).getTmp().getMin() + "°");
                break;
            case 3:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(4).getTmp().getMin() + "°");
                break;
            case 4:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(5).getTmp().getMin() + "°");
                break;
            case 5:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(6).getTmp().getMin() + "°");
                break;
            default:
                break;
        }


    }

    private void setMaxTemp(int i, int j) {
        switch (j) {
            case 0:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(1).getTmp().getMax() + "°");
                break;
            case 1:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(2).getTmp().getMax() + "°");
                break;
            case 2:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(3).getTmp().getMax() + "°");
                break;
            case 3:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(4).getTmp().getMax() + "°");
                break;
            case 4:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(5).getTmp().getMax() + "°");
                break;
            case 5:
                tempText[i][j].setText(
                        heWeather.getDaily_forecast().get(6).getTmp().getMax() + "°");
                break;
            default:
                break;
        }
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//        overridePendingTransition(R.anim.in_from_up, R.anim.out_to_down); // 由左向右滑入的效果
//    }

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
                if (upY - downY > 100) {
                    finish();
                    overridePendingTransition(R.anim.in_from_up, R.anim.out_to_down);
                }
                if (downY - upY > 100) {
                    Intent intent = new Intent(this, TodayWeatherActivity.class);
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
