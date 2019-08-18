package ca.judacribz.week4weekend_retrofit_rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote.OpenWeatherHelper;
import ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote.OpenWeatherObserver;
import ca.judacribz.week4weekend_retrofit_rxjava.model.events.OpenWeatherEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static ca.judacribz.week4weekend_retrofit_rxjava.model.weather.OpenWeather.APP_ID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRxJavaCall();
    }

    public void initRxJavaCall() {
        OpenWeatherHelper.getObsService().getHourlyForecast(String.valueOf(30339), APP_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new OpenWeatherObserver());
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getOpenWeather(OpenWeatherEvent event) {
        Log.d("YOO", "getOpenWeather: " + event.getOpenWeather().getList().size());
    }
}
