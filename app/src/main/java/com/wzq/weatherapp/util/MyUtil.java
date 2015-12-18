package com.wzq.weatherapp.util;

import com.wzq.weatherapp.R;

public class MyUtil {

	public int setWeatherIcon(String codeid){
		int code = Integer.parseInt(codeid);
		int icon = 999;
		switch (code){
			case 100:
				icon = R.drawable.icon_100;
				break;
			case 101:
				icon = R.drawable.icon_101;
				break;
			case 102:
				icon = R.drawable.icon_102;
				break;
			case 103:
				icon = R.drawable.icon_103;
				break;
			case 104:
				icon = R.drawable.icon_104;
				break;
			case 200:
				icon = R.drawable.icon_200;
				break;
			case 201:
				icon = R.drawable.icon_201;
				break;
			case 202:
				icon = R.drawable.icon_202;
				break;
			case 203:
				icon = R.drawable.icon_203;
				break;
			case 204:
				icon = R.drawable.icon_204;
				break;
			case 205:
				icon = R.drawable.icon_205;
				break;
			case 206:
				icon = R.drawable.icon_206;
				break;
			case 207:
				icon = R.drawable.icon_207;
				break;
			case 208:
				icon = R.drawable.icon_208;
				break;
			case 209:
				icon = R.drawable.icon_209;
				break;
			case 210:
				icon = R.drawable.icon_210;
				break;
			case 211:
				icon = R.drawable.icon_211;
				break;
			case 212:
				icon = R.drawable.icon_212;
				break;
			case 213:
				icon = R.drawable.icon_213;
				break;
			case 300:
				icon = R.drawable.icon_300;
				break;
			case 301:
				icon = R.drawable.icon_301;
				break;
			case 302:
				icon = R.drawable.icon_302;
				break;
			case 303:
				icon = R.drawable.icon_303;
				break;
			case 304:
				icon = R.drawable.icon_304;
				break;
			case 305:
				icon = R.drawable.icon_305;
				break;
			case 306:
				icon = R.drawable.icon_306;
				break;
			case 307:
				icon = R.drawable.icon_307;
				break;
			case 308:
				icon = R.drawable.icon_308;
				break;
			case 309:
				icon = R.drawable.icon_309;
				break;
			case 310:
				icon = R.drawable.icon_310;
				break;
			case 311:
				icon = R.drawable.icon_311;
				break;
			case 312:
				icon = R.drawable.icon_312;
				break;
			case 313:
				icon = R.drawable.icon_313;
				break;
			case 400:
				icon = R.drawable.icon_400;
				break;
			case 401:
				icon = R.drawable.icon_401;
				break;
			case 402:
				icon = R.drawable.icon_402;
				break;
			case 403:
				icon = R.drawable.icon_403;
				break;
			case 404:
				icon = R.drawable.icon_404;
				break;
			case 405:
				icon = R.drawable.icon_405;
				break;
			case 406:
				icon = R.drawable.icon_406;
				break;
			case 407:
				icon = R.drawable.icon_407;
				break;
			case 500:
				icon = R.drawable.icon_500;
				break;
			case 501:
				icon = R.drawable.icon_501;
				break;
			case 502:
				icon = R.drawable.icon_502;
				break;
			case 503:
				icon = R.drawable.icon_503;
				break;
			case 504:
				icon = R.drawable.icon_504;
				break;
			case 507:
				icon = R.drawable.icon_507;
				break;
			case 508:
				icon = R.drawable.icon_508;
				break;
			case 900:
				icon = R.drawable.icon_900;
				break;
			case 901:
				icon = R.drawable.icon_901;
				break;
			case 999:
				icon = R.drawable.icon_999;
				break;
			default:
				break;
		}
		return icon;
	};

	public int  getDrawable(String weather){
		int drawableID = 0;
		switch (weather) {
			case "晴":
				drawableID = R.drawable.qingtain2;
				break;
			case "多云":
				drawableID = R.drawable.duoyun;
				break;
			case "雷阵雨":
				drawableID = R.drawable.leizhenyu;
				break;
			case "阵雨":
				drawableID = R.drawable.xiaoyu;
				break;
			case "雷阵雨转阵雨" :
				drawableID = R.drawable.leizhenyu;
				break;
			case "小雨":
				drawableID = R.drawable.xiaoyu;
				break;
			case "中雨":
				drawableID = R.drawable.zhongyu;
				break;
			case "大雨":
				drawableID = R.drawable.dayu;
				break;
			case "暴雨":
				drawableID = R.drawable.baoyu;
				break;
			case "阴":
				drawableID = R.drawable.yingtian;
				break;
			case "阴转多云":
				drawableID = R.drawable.duoyun;
				break;
			case "多云转阴":
				drawableID = R.drawable.duoyun;
				break;
			case "晴转多云":
				drawableID = R.drawable.duoyun;
				break;
			case "雷阵雨转多云":
				drawableID = R.drawable.leizhenyu;
				break;
			case "中雨转阵雨":
				drawableID = R.drawable.zhongyu;
				break;
			case "小雨转晴":
				drawableID = R.drawable.xiaoyu;
				break;
			case "中到大雨转阵雨":
				drawableID = R.drawable.zhongyu;
				break;
			case "中到大雨":
				drawableID = R.drawable.zhongyu;
				break;
			case "阵雨转多云":
				drawableID = R.drawable.xiaoyu;
				break;
			case "多云转晴":
				drawableID = R.drawable.duoyun;
				break;
			case "中到大雨转雷阵雨":
				drawableID = R.drawable.dayu;
				break;
			default:
				break;
		}
		return drawableID;
	}
}
