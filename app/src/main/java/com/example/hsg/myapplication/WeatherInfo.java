package com.example.hsg.myapplication;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by hsg on 2017. 8. 4..
 */

public interface WeatherInfo {

    //현재 시간 받아오는 api...
    //http://apis.skplanetx.com/weather/current/hourly?version={version}&lat={lat}&lon={lon}&city={city}&county={county}&village={village}

    public static final String URL_PARS = "http://apis.skplanetx.com/"; // Sk planet

    @Headers({"appKey: 6bff357e-71e3-317e-9984-af827bda68bd"})

    //시간 단위로 받아옴
    @GET("weather/current/hourly")
    Call<React> get_Weather(@Query("version") int version, @Query("lat") double lat, @Query("lon") double lon);


}
