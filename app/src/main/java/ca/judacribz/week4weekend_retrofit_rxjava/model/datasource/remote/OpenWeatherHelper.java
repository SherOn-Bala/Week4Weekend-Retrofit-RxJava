package ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote;


import androidx.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import ca.judacribz.week4weekend_retrofit_rxjava.model.events.HourlyForecastEvent;
import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.HourlyForecast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static ca.judacribz.week4weekend_retrofit_rxjava.model.weather.HourlyForecast.APP_ID;
import static ca.judacribz.week4weekend_retrofit_rxjava.model.weather.HourlyForecast.BASE_URL;

public class OpenWeatherHelper {


    public static Retrofit getOpenWeatherInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static OpenWeatherService getService() {
        return getOpenWeatherInstance().create(OpenWeatherService.class);
    }

    public static ObservableOpenWeatherService getObsService() {
        return getOpenWeatherInstance().create(ObservableOpenWeatherService.class);
    }

//    public static void getCurrentWeather(int zip) {
//        getService()
//                .getCurrentWeather(String.valueOf(zip), APP_ID)
//                .enqueue(new Callback<HourlyForecast>() {
//                    @Override
//                    public void onResponse(@NonNull Call<HourlyForecast> call,
//                                           @NonNull Response<HourlyForecast> response) {
//                        EventBus.getDefault().post(new HourlyForecastEvent(response.body()));
//                    }
//
//                    @Override
//                    public void onFailure(Call<HourlyForecast> call, Throwable t) {
//                    }
//                });
//    }
}
