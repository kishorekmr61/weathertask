package com.example.weatherapp.MainActivity;

import android.content.Context;

import com.example.weatherapp.Base.BaseContract;
import com.example.weatherapp.Pojo.WeatherResponse;

public interface MainActivityContracter {

    interface View extends BaseContract.View {
        void setViews();


        void onCompplete(WeatherResponse weatherResponse);

        void error(Throwable error);

    }


    interface Presenter extends BaseContract.Presenter<View> {
        void getWeatherReport(double latitude, double longitude);
    }
}
