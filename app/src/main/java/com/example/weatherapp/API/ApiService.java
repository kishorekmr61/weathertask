package com.example.weatherapp.API;


import com.example.weatherapp.Pojo.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kishore
 */

public interface ApiService {

    @GET("weather?")
    Observable<WeatherResponse> getWeatherResponse(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appid);

}
