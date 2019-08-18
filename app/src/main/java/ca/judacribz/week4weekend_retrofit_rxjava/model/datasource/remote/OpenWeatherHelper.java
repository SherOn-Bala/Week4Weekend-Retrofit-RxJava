package ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote;


import org.greenrobot.eventbus.EventBus;

import ca.judacribz.week4weekend_retrofit_rxjava.model.events.OpenWeatherEvent;
import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.OpenWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static ca.judacribz.week4weekend_retrofit_rxjava.model.weather.OpenWeather.APP_ID;
import static ca.judacribz.week4weekend_retrofit_rxjava.model.weather.OpenWeather.BASE_URL;

public class OpenWeatherHelper {
    public static Retrofit getRetroFitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static OpenWeatherService getService() {
        return getRetroFitInstance().create(OpenWeatherService.class);
    }

    public static ObservableOpenWeatherService getObsService() {
        return getRetroFitInstance().create(ObservableOpenWeatherService.class);
    }

    public static void getOpenWeather(int zip) {
        getService()
                .getHourlyForecast(String.valueOf(zip), APP_ID)
                .enqueue(new Callback<OpenWeather>() {
                    @Override
                    public void onResponse(Call<OpenWeather> call, Response<OpenWeather> response) {
                        EventBus.getDefault().post(new OpenWeatherEvent(response.body()));
                    }

                    @Override
                    public void onFailure(Call<OpenWeather> call, Throwable t) {
                    }
                });
    }
}
