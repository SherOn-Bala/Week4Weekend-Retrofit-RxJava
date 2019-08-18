package ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import ca.judacribz.week4weekend_retrofit_rxjava.model.events.OpenWeatherEvent;
import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.OpenWeather;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OpenWeatherObserver implements Observer<OpenWeather> {

    private static final String TAG = OpenWeatherObserver.class.getSimpleName();

    private OpenWeather openWeather;

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
    }

    @Override
    public void onNext(OpenWeather openWeather) {
        Log.d(TAG, "onNext: ");
        this.openWeather = openWeather;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void onComplete() {
        EventBus.getDefault().post(new OpenWeatherEvent(openWeather));
    }
}
