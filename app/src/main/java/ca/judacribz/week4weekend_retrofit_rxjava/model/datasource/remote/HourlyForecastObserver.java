package ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import ca.judacribz.week4weekend_retrofit_rxjava.model.events.HourlyForecastEvent;
import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.HourlyForecast;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HourlyForecastObserver implements Observer<HourlyForecast> {

    private static final String TAG = HourlyForecastObserver.class.getSimpleName();

    private HourlyForecast hourlyForecast;

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(TAG, "onSubscribe: ");
    }

    @Override
    public void onNext(HourlyForecast hourlyForecast) {
        Log.d(TAG, "onNext: ");
        this.hourlyForecast = hourlyForecast;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void onComplete() {
        EventBus.getDefault().post(new HourlyForecastEvent(hourlyForecast));
    }
}
