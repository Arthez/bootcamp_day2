package com.droidonroids.weatherbootcamp.data.network.entities;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface WeatherApiService {

        @GET("/forecast")
        ForecastResponse getForecastWithAsync(
                @Query("q") String cityName);

        @GET("/forecast")
        void getForecastUsingCallback(
                @Query("q") String cityName, Callback<ForecastResponse> callback);

}
