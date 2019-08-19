package ca.judacribz.week4weekend_retrofit_rxjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.List;

import static ca.judacribz.week4weekend_retrofit_rxjava.model.weather.Constants.WEATHER_ICON;
import static ca.judacribz.week4weekend_retrofit_rxjava.util.Calculate.kelvinToFahrenheit;

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder> {

    Context context;
    java.util.List<List> hourlyForecast;

    double
            max,
            min;
    boolean
            maxSet = false,
            minSet = false;

    public HourlyForecastAdapter(Context context, ArrayList<List> hourlyForecast) {
        this.context = context;
        this.hourlyForecast = hourlyForecast;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_hourly,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setViews(hourlyForecast.get(position));
    }

    @Override
    public int getItemCount() {
        return hourlyForecast.size();
    }

    void setData(java.util.List<List> list) {
        hourlyForecast = list.subList(0, 9);
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        double temp;
        for (List forecast : hourlyForecast) {
            temp = forecast.getMain().getTemp();
            min = Math.min(min, temp);
            max = Math.max(max, temp);
        }

        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivWeather;

        TextView
                tvTime,
                tvWeatherDesc,
                tvTemperature;

        ViewHolder(View itemView) {
            super(itemView);

            tvTime = itemView.findViewById(R.id.tvTime);
            ivWeather = itemView.findViewById(R.id.ivWeather);
            tvWeatherDesc = itemView.findViewById(R.id.tvWeatherDesc);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
        }

        void setViews(List forecast) {
            int time = Integer.valueOf(forecast.getDtTxt().substring(11, 13));
            time -= 4 - (time < 4 ? (24) : 0);


            tvTime.setText(String.format(Locale.US, "%d:00", time));
            Glide
                    .with(context)
                    .load(String.format(
                            Locale.US,
                            WEATHER_ICON,
                            forecast.getWeather().get(0).getIcon()
                    ))
                    .into(ivWeather);

            tvWeatherDesc.setText(forecast.getWeather().get(0).getMain());

            double temp = forecast.getMain().getTemp();
            tvTemperature.setText(String.format(
                    Locale.US,
                    "%.0f" + context.getString(R.string.fahrenheit),
                    kelvinToFahrenheit(temp)
            ));

            if (!maxSet) {
                if (temp == max) {
                    maxSet = true;
                    tvTemperature.setBackgroundColor(context.getResources().getColor(R.color.warm));
                }
            }

            if (!minSet) {
                if (temp == min) {
                    minSet = true;
                    tvTemperature.setBackgroundColor(context.getResources().getColor(R.color.cool));
                }
            }


        }
    }
}
