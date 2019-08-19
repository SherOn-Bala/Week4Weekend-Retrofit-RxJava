package ca.judacribz.week4weekend_retrofit_rxjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Locale;

import ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote.CurrentWeatherObserver;
import ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote.OpenWeatherHelper;
import ca.judacribz.week4weekend_retrofit_rxjava.model.datasource.remote.HourlyForecastObserver;
import ca.judacribz.week4weekend_retrofit_rxjava.model.events.CurrentWeatherEvent;
import ca.judacribz.week4weekend_retrofit_rxjava.model.events.HourlyForecastEvent;
import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.HourlyForecast;
import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static ca.judacribz.week4weekend_retrofit_rxjava.model.weather.Constants.WEATHER_ICON;
import static ca.judacribz.week4weekend_retrofit_rxjava.model.weather.HourlyForecast.APP_ID;
import static ca.judacribz.week4weekend_retrofit_rxjava.util.Calculate.kelvinToFahrenheit;

public class MainActivity extends AppCompatActivity {

    private static final String ZIP_CODE = "ZIP_CODE";
    private static final String TAG = MainActivity.class.getSimpleName();
    SharedPreferences sharedPref;
    int zip;

    TextView
            tvZip,
            tvTemperature,
            tvUnit;

    ImageView ivWeather;

    RecyclerView rvHourlyForecast;
    HourlyForecastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvZip = findViewById(R.id.tvZip);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvUnit = findViewById(R.id.tvUnit);
        ivWeather = findViewById(R.id.ivWeather);

        rvHourlyForecast = findViewById(R.id.rvHourlyForecast);
        rvHourlyForecast.setLayoutManager(new LinearLayoutManager(
                this,
                RecyclerView.HORIZONTAL,
                false
        ));

        rvHourlyForecast.setAdapter(adapter = new HourlyForecastAdapter(
                this,
                new ArrayList<List>())
        );

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        zip = sharedPref.getInt(ZIP_CODE, -1);

        if (zip == -1) {
            launchDialog();
        } else {
            updateWeather();
        }
    }

    public void launchDialog(View view) {
        launchDialog();
    }

    private void launchDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Enter Zipcode");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        if (zip == -1) {
            builder.setCancelable(false);
        }
        builder.setView(input);

        builder.setPositiveButton("Enter", null);
        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = input.getText().toString();
                if (str.length() < 5) {
                    input.setError(getString(R.string.err_valid_zipcode));
                } else {
                    sharedPref.edit().putInt(ZIP_CODE, zip = Integer.valueOf(str)).apply();
                    updateWeather();
                    dialog.dismiss();
                }
            }
        });
    }

    private void updateWeather() {
        tvZip.setText(String.format(getString(R.string.zip), zip));
        initRxJavaCall(zip);
    }

    public void initRxJavaCall(int zip) {
        OpenWeatherHelper.getObsService().getCurrentWeather(String.valueOf(zip), APP_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CurrentWeatherObserver());


        OpenWeatherHelper.getObsService().getHourlyForecast(String.valueOf(zip), APP_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new HourlyForecastObserver());
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
    public void currentWeatherReceived(CurrentWeatherEvent event) {

        List currentWeather = event.getCurrentWeather();
        Log.d(TAG, "currentWeatherReceived: " + currentWeather.getMain().getTemp());
        updateWeatherUI(currentWeather);
    }

    private void updateWeatherUI(List currentWeather) {
        double temp = kelvinToFahrenheit(currentWeather.getMain().getTemp());
        tvTemperature.setText(String.format(
                Locale.US,
                "%.1f",
                temp
        ));

        tvTemperature.setBackground(getDrawable(
                temp >= 60 ?
                        R.drawable.circle_warm :
                        R.drawable.circle_cool
        ));

        Glide
                .with(this)
                .load(String.format(
                        Locale.US,
                        WEATHER_ICON,
                        currentWeather.getWeather().get(0).getIcon()
                ))
                .into(ivWeather);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hourlyForecastReceived(HourlyForecastEvent event) {

        HourlyForecast hourlyForecast = event.getHourlyForecast();
        Log.d(TAG, "hourlyForecastReceived: " + hourlyForecast.getCity().getName());

        updateForecastUI(hourlyForecast);
    }

    private void updateForecastUI(HourlyForecast hourlyForecast) {
        getSupportActionBar().setTitle(String.format(
                Locale.US,
                "Umbrella: %s",
                hourlyForecast.getCity().getName()
        ));

        adapter.setData(hourlyForecast.getList());
    }
}
