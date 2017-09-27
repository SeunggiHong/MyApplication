package com.example.hsg.myapplication;

import com.example.hsg.myapplication.model.Common;
import com.example.hsg.myapplication.model.Grid;
import com.example.hsg.myapplication.model.Hourly;
import com.example.hsg.myapplication.model.Precipitation;
import com.example.hsg.myapplication.model.Result;
import com.example.hsg.myapplication.model.Sky;
import com.example.hsg.myapplication.model.Temperature;
import com.example.hsg.myapplication.model.Weather;
import com.example.hsg.myapplication.model.Wind;

/**
 * Created by hsg on 2017. 8. 4..
 */

public class React {



   Common common;

   Result result;

   Weather weather;

   public Common getCommon() {
      return common;
   }

   public void setCommon(Common common) {
      this.common = common;
   }

   public Result getResult() {
      return result;
   }

   public void setResult(Result result) {
      this.result = result;
   }

   public Weather getWeather() {
      return weather;
   }

   public void setWeather(Weather weather) {
      this.weather = weather;
   }
}
