package com.example.weatherapp.MainActivity;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.example.weatherapp.API.APIClient;
import com.example.weatherapp.API.ApiService;
import com.example.weatherapp.Base.BasePresenter;
import com.example.weatherapp.Utilities.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivityPresenter extends BasePresenter<MainActivityContracter.View> implements MainActivityContracter.Presenter {
    CompositeDisposable compositeDisposable;
    private ApiService apiservice;


    @Override
    public void onPresenterCreated() {

    }

    public MainActivityPresenter() {
        compositeDisposable = new CompositeDisposable();
        apiservice = APIClient.getAPIService();
    }

    @Override
    public void getWeatherReport(double latitude, double longitude) {
        compositeDisposable.add(apiservice.getWeatherResponse(latitude, longitude, Constants.appid)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                    getView().onCompplete(value);
                }, error -> {
                    getView().error(error);
                }));
    }
}
