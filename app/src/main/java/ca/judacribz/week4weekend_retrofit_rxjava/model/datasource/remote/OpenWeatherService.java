package ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote;

import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.OpenWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {
    @GET("data/2.5/forecast/")
    Call<OpenWeather> getHourlyForecast(@Query("zip") String zip,
                                        @Query("appid") String appid);
}
