package com.example.hsg.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

    // 버전은 무조건 1로 넣는다
    int version = 1;

    final static String TAG = "WeatherINFo";

    int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 999;

    React React;

    private TextView tv_tem, tv_city, tv_state, tv_admin, tv_time, min_tem, max_tem;
    private ImageView weathericon;

    //위도
    double lat;
    //경도
    double lon;

    //디바이스의 위치를 가져옴.
    private LocationManager locationManager;
    //위치가 변할때마다 위치를 가져오는 리스너.
    private LocationListener locationListener;

    private Location getMyLocation() {
        Location currentLocation = null;
        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // 사용자 권한 요청
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            // 수동으로 위치 구하기
            String locationProvider = LocationManager.GPS_PROVIDER;
            currentLocation = locationManager.getLastKnownLocation(locationProvider);
            if (currentLocation != null) {
                 lon = currentLocation.getLongitude();
                 lat = currentLocation.getLatitude();
                Log.e("Main", "longtitude=" + lon + ", latitude=" + lat);

                locationManager.removeUpdates(locationListener);
                run();
            }
        }
        return currentLocation;
    }

    private void settingGPS() {
        // Acquire a reference to the system Location Manager


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();

                Log.e("Sub", "longtitude=" + lat + ", latitude=" + lon);

                locationManager.removeUpdates(locationListener);

                run();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
            public void onProviderEnabled(String provider) {
            }
            public void onProviderDisabled(String provider) {
            }
        };
    }



    public void run() {

        Retrofit client = new Retrofit.Builder().baseUrl("http://apis.skplanetx.com/").addConverterFactory(GsonConverterFactory.create()).build();
        WeatherInfo service = client.create(WeatherInfo.class);
        Call<React> call = service.get_Weather(version, lat, lon);

        call.enqueue(new Callback<React>() {

            @Override
            public void onResponse(Call<React> call, Response<React> response) {

                if (response.isSuccessful()) {
                    React = response.body();
                    Log.d(TAG, "response.raw :" + response.raw());



                    if (React.getResult().getCode() == 9200) { // 9200 성공코드

                        //현재 도시 구하기
                        tv_city.setText(React.getWeather().getHourly()[0].getGrid().getCity());
                        //현재온도
                        tv_tem.setText(String.format("%.2s", React.getWeather().getHourly()[0].getTemperature().getTc()) +'c') ;
                        //최고온도
                        max_tem.setText(String.format("%.2s", React.getWeather().getHourly()[0].getTemperature().getTmax()) +'c') ;
                        //최저온도
                        min_tem.setText(String.format("%.2s", React.getWeather().getHourly()[0].getTemperature().getTmin()) +'c') ;

                        tv_admin.setText(React.getWeather().getHourly()[0].getGrid().getCounty() + ' ' +React.getWeather().getHourly()[0].getGrid().getVillage());
                        tv_state.setText(React.getWeather().getHourly()[0].getSky().getName());
                        tv_time.setText(React.getWeather().getHourly()[0].getTimeRelease());

                        //날씨 정보 아이콘 구하기
                        weathericon.setBackgroundResource(getWeatherIcon(React.getWeather().getHourly()[0].getSky().getCode()));


                        //12:00 AM TUESDAY

                        SimpleDateFormat date = new SimpleDateFormat("HH:MM aa EEEE");
                        Date today = new Date();
                        tv_time.setText(date.format(today));



                    } else {
                        Log.e(TAG, "요청 실패 :" + React.getResult().getCode());
                        Log.e(TAG, "메시지 :" + React.getResult().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<React> call, Throwable t) {
                Log.e(TAG, "날씨정보 불러오기 실패 :" + t.getMessage());
                Log.e(TAG, "요청 메시지 :" + call.request());
            }
        });
    }

    //날씨 아이콘 구하는 함수
    private int getWeatherIcon(String code){

        switch (code) {
            case "Sky_O01":
                return R.drawable.sky_o01;

            case "Sky_O02":
                return R.drawable.sky_o02;

            case "SKY_O03":
                return R.drawable.sky_o03;

            case "SKY_O04":
                return R.drawable.sky_o04;

            case "SKY_O05":
                return R.drawable.sky_o05;

            case "SKY_O06":
                return R.drawable.sky_o06;

            case "SKY_O07":
                return R.drawable.sky_o07;

            case "SKY_O08":
                return R.drawable.sky_o08;

            case "SKY_O09":
                return R.drawable.sky_o09;

            case "SKY_O10":
                return R.drawable.sky_o010;

            case "SKY_O11":
                return R.drawable.sky_o011;

            case "SKY_O12":
                return R.drawable.sky_o012;

            case "SKY_O13":
                return R.drawable.sky_o013;

            case "SKY_O14":
                return R.drawable.sky_o014;

            default:
                return R.drawable.sky_o01;

        }



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 3) {
            // Make sure the request was successful

            Toast.makeText(getApplicationContext(), "출력할 문자열", Toast.LENGTH_LONG).show();

            if (resultCode != 3) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tv_tem      = (TextView)  findViewById(R.id.tv_tem);
        tv_city     = (TextView)  findViewById(R.id.tv_city);
        tv_admin    = (TextView)  findViewById(R.id.tv_admin);
        tv_state    = (TextView)  findViewById(R.id.tv_state);
        tv_time     = (TextView)  findViewById(R.id.tv_time);
        min_tem     = (TextView)  findViewById(R.id.min_tem);
        max_tem     = (TextView)  findViewById(R.id.max_tem);
        weathericon = (ImageView) findViewById(R.id.weathericon);




       Intent intent = getIntent();


        //값이 넘어오는지 확인
        if (intent.hasExtra("lat_ob")&&intent.hasExtra("lon_ob")) {

            double lat_list;
            double lon_list;

            lat_list = intent.getExtras().getDouble("lat_ob");
            lon_list = intent.getExtras().getDouble("lon_ob");

            Log.e(TAG, "list_geo" + lat_list + lon_list);

        }else {

            settingGPS();
            getMyLocation();

        }









    }

}
