package ca.judacribz.week4weekend_retrofit_rxjava.model.events;

import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.OpenWeather;

public class OpenWeatherEvent {
    OpenWeather openWeather;

    public OpenWeatherEvent(OpenWeather openWeather) {
        this.openWeather = openWeather;
    }

    public OpenWeather getOpenWeather() {
        return openWeather;
    }

    public void setOpenWeather(OpenWeather openWeather) {
        this.openWeather = openWeather;
    }
}
