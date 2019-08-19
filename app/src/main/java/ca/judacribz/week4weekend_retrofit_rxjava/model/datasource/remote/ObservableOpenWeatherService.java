package ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote;

import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.HourlyForecast;
import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ObservableOpenWeatherService {
    @GET("data/2.5/weather/")
    Observable<List> getCurrentWeather(@Query("zip") String zip,
                                       @Query("appid") String appid);

    @GET("data/2.5/forecast/")
    Observable<HourlyForecast> getHourlyForecast(@Query("zip") String zip,
                                                 @Query("appid") String appid);
}
