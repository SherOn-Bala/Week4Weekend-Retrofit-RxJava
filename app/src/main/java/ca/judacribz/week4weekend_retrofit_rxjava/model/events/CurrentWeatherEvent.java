package ca.judacribz.week4weekend_retrofit_rxjava.model.events;

import ca.judacribz.week4weekend_retrofit_rxjava.model.weather.List;

public class CurrentWeatherEvent {
    List currentWeather;

    public CurrentWeatherEvent(List currentWeather) {
        this.currentWeather = currentWeather;
    }

    public List getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(List currentWeather) {
        this.currentWeather = currentWeather;
    }
}
