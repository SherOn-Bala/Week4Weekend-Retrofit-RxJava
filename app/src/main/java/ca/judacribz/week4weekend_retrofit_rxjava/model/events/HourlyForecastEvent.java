package ca.judacribz.week4weekend_retrofit_rxjava.model.events;

import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.HourlyForecast;

public class HourlyForecastEvent {
    HourlyForecast hourlyForecast;

    public HourlyForecastEvent(HourlyForecast hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

    public HourlyForecast getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(HourlyForecast hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }
}
