package ca.judacribz.week4weekend_retrofit_rxjava.util;

public class Calculate {

    public static double kelvinToCelcius(double kelvin) {
        return kelvin - 273.15;
    }

    public static double kelvinToFahrenheit(double kelvin) {
        return 9./5.*(kelvin - 273.15) + 32;
    }
}
