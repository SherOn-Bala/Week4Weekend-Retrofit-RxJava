package ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote;

import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.HourlyForecast;
import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {

    @GET("data/2.5/weather/")
    Call<List> getCurrentWeater(@Query("zip") String zip,
                                @Query("appid") String appid);

    @GET("data/2.5/forecast/")
    Call<HourlyForecast> getHourlyForecast(@Query("zip") String zip,
                                           @Query("appid") String appid);
}
