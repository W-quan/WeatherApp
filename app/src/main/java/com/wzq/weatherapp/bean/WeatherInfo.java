package com.wzq.weatherapp.bean;

import java.util.List;

/**
 * Created by wzq on 15-12-12.
 */

public class WeatherInfo {
    private List<HeWeather> heWeathers;

    public List<HeWeather> getHeWeathers() {
        return heWeathers;
    }

    public static class HeWeather {
        private String status;          //接口状态，参考http://www.heweather.com/documents/api
        private Basic basic;            //基本信息
        private Now now;                //实况天气
        private Aqi aqi;                //空气质量，仅限国内部分城市，国际城市无此字段
        private List<DailyForecast> daily_forecast;    //7天天气预报
        private List<HourlyForecast> hourly_forecast;  //每三小时天气预报，全能版为每小时预报
        private Suggestion suggestion;          //生活指数，仅限国内城市，国际城市无此字段

        public static class Basic {
            private String city;        //城市名称
            private String cnty;        //国家
            private String id;          //城市ID，参见http://www.heweather.com/documents/cn-city-list
            private String lat;         //城市维度
            private String lon;         //城市经度
            private Update update;      //更新时间

            public static class Update {
                private String loc;     //当地时间
                private String utc;     //UTC时间

                public String getUtc() {
                    return utc;
                }

                public String getLoc() {
                    return loc;
                }
            }

            public String getCity() {
                return city;
            }

            public String getCnty() {
                return cnty;
            }

            public String getId() {
                return id;
            }

            public String getLat() {
                return lat;
            }

            public String getLon() {
                return lon;
            }

            public Update getUpdate() {
                return update;
            }
        }

        public static class Now {
            private Cond cond;      //天气状况
            private String fl;      //体感温度
            private String hum;     //相对湿度（%）
            private String pcpn;    //降水量（mm）
            private String pres;    //气压
            private String tmp;     //温度
            private String vis;     //能见度（km）
            private Wind wind;      //风力风向

            public static class Cond {
                private String code;     //天气状况代码
                private String txt;      //天气状况描述

                public String getTxt() {
                    return txt;
                }
                public String getCode() {
                    return code;
                }
            }

            public static class Wind {
                private String deg;     //风向（360度）
                private String dir;     //风向
                private String sc;      //风力
                private String spd;     //风速（kmph）

                public String getDeg() {
                    return deg;
                }

                public String getDir() {
                    return dir;
                }

                public String getSc() {
                    return sc;
                }

                public String getSpd() {
                    return spd;
                }
            }

            public Cond getCond() {
                return cond;
            }

            public String getFl() {
                return fl;
            }

            public String getHum() {
                return hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public String getPres() {
                return pres;
            }

            public String getTmp() {
                return tmp;
            }

            public String getVis() {
                return vis;
            }

            public Wind getWind() {
                return wind;
            }
        }

        public static class Aqi {
            private City city;

            public City getCity() {
                return city;
            }

            public void setCity(City city) {
                this.city = city;
            }

            public static class City {
                private String aqi;         //空气质量指数
                private String co;          //一氧化碳1小时平均值(ug/m³)
                private String no2;         //二氧化氮1小时平均值(ug/m³)
                private String o3;          //臭氧1小时平均值(ug/m³)
                private String pm10;        //PM10 1小时平均值(ug/m³)
                private String pm25;        //PM2.5 1小时平均值(ug/m³)
                private String qlty;        //空气质量类别
                private String so2;         //二氧化硫1小时平均值(ug/m³)

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }
        }

        public static class DailyForecast {
            private String date;            //预报日期
            private Astro astro;            //天文数值
            private Cond cond;              //天气状况
            private String hum;             //相对湿度（%）
            private String pcpn;            //降水量（mm）
            private String pop;             //降水概率
            private String pres;            //气压
            private Tmp tmp;                //温度
            private String vis;             //能见度（km）
            private Wind wind;              //风力风向

            public static class Astro {
                private String sr;          //日出时间
                private String ss;          //日落时间

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class Cond {
                private String code_d;      //白天天气状况代码 参考http://www.heweather.com/documents/condition-code
                private String code_n;      //夜晚天气代码
                private String txt_d;       //白天天气状况描述
                private String txt_n;       //夜间天气状况描述

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class Tmp {
                private String max;         //最高温度
                private String min;         //最低温度

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class Wind {
                private String deg;         //风向（360度）
                private String dir;         //风向
                private String sc;          //风力
                private String spd;         //风速（kmph）

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public Astro getAstro() {
                return astro;
            }

            public void setAstro(Astro astro) {
                this.astro = astro;
            }

            public Cond getCond() {
                return cond;
            }

            public void setCond(Cond cond) {
                this.cond = cond;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public Tmp getTmp() {
                return tmp;
            }

            public void setTmp(Tmp tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public Wind getWind() {
                return wind;
            }

            public void setWind(Wind wind) {
                this.wind = wind;
            }
        }

        public static class HourlyForecast {
            private String date;           //时间
            private String hum;            //相对湿度（%）
            private String pop;            //降水概率
            private String pres;           //气压
            private String tmp;            //温度
            private Wind wind;             //风力风向

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public Wind getWind() {
                return wind;
            }

            public void setWind(Wind wind) {
                this.wind = wind;
            }

            private static class Wind {
                private String deg;         //风向（360度）
                private String dir;         //风向
                private String sc;          //风力
                private String spd;         //风速（kmph）

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class Suggestion {
            private Comf comf;           //舒适度指数
            private Cw cw;               //洗车指数
            private Drsg drsg;           //穿衣指数
            private Flu flu;             //感冒指数
            private Sport sport;         //运动指数
            private Trav trav;           //旅游指数
            private Uv uv;               //紫外线指数

            private static class Comf {
                private String brf;         //简介
                private String txt;         //详细描述

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            private static class Cw {
                private String brf;         //简介
                private String txt;         //详细描述

                public String getTxt() {
                    return txt;
                }

                public String getBrf() {
                    return brf;
                }
            }

            private static class Drsg {
                private String brf;         //简介
                private String txt;         //详细描述

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            private static class Flu {
                private String brf;         //简介
                private String txt;         //详细描述

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            private static class Sport {
                private String brf;         //简介
                private String txt;         //详细描述

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            private static class Trav {
                private String brf;         //简介
                private String txt;         //详细描述

                public String getBrf() {
                    return brf;
                }

                public String getTxt() {
                    return txt;
                }
            }

            private static class Uv {
                private String brf;         //简介
                private String txt;         //详细描述

                public String getTxt() {
                    return txt;
                }

                public String getBrf() {
                    return brf;
                }
            }

            public Comf getComf() {
                return comf;
            }

            public Cw getCw() {
                return cw;
            }

            public Drsg getDrsg() {
                return drsg;
            }

            public Flu getFlu() {
                return flu;
            }

            public Sport getSport() {
                return sport;
            }

            public Trav getTrav() {
                return trav;
            }

            public Uv getUv() {
                return uv;
            }
        }

        public String getStatus() {
            return status;
        }

        public Basic getBasic() {
            return basic;
        }

        public Now getNow() {
            return now;
        }

        public Aqi getAqi() {
            return aqi;
        }

        public List<DailyForecast> getDaily_forecast() {
            return daily_forecast;
        }

        public List<HourlyForecast> getHourly_forecast() {
            return hourly_forecast;
        }

        public Suggestion getSuggestion() {
            return suggestion;
        }
    }
}
