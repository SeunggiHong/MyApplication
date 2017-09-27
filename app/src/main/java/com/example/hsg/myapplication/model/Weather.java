package com.example.hsg.myapplication.model;

/**
 * Created by hsg on 2017. 8. 14..
 */

public class Weather {

    public Hourly[] getHourly() {
        return hourly;
    }

    public void setHourly(Hourly[] hourly) {
        this.hourly = hourly;
    }

    Hourly hourly[];

}
