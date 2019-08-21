package ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static ca.judacribz.week4weekend_retrofit_rxjava.model.weather.Constants.BASE_URL;

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
