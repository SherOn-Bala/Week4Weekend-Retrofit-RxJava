package ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import ca.judacribz.week4weekend_retrofit_rxjava.model.events.CurrentWeatherEvent;
import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.List;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CurrentWeatherObserver implements Observer<List> {

    private static final String TAG = CurrentWeatherObserver.class.getSimpleName();

    private List currentWeather;

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
    }

    @Override
    public void onNext(List currentWeather) {
        Log.d(TAG, "onNext: ");
        this.currentWeather = currentWeather;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void onComplete() {
        EventBus.getDefault().post(new CurrentWeatherEvent(currentWeather));
    }
}
