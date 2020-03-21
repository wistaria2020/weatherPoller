package ds.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Location {

    private String name;
    private double latitude;
    private double longitude;

    public Location() {
    }

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private double temperatureAlertLow = - 273.15;
    private double temperatureAlertHigh = 300;

    private int pollingIntervalMinutes = 60;

    private LocalDateTime nextPolling = LocalDateTime.now();

    private Temperature temperature;

    //too low or too high temperature
    public boolean isTemperatureAbnormal() {
        return   temperature.isKnown() &&
                (temperature.getValue() < temperatureAlertLow
                ||  temperature.getValue() > temperatureAlertHigh);
    }



    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public double getTemperatureAlertLow() {
        return temperatureAlertLow;
    }

    public void setTemperatureAlertLow(double temperatureAlertLow) {
        this.temperatureAlertLow = temperatureAlertLow;
    }

    public double getTemperatureAlertHigh() {
        return temperatureAlertHigh;
    }

    public void setTemperatureAlertHigh(double temperatureAlertHigh) {
        this.temperatureAlertHigh = temperatureAlertHigh;
    }

    public int getPollingIntervalMinutes() {
        return pollingIntervalMinutes;
    }

    public void setPollingIntervalMinutes(int pollingIntervalMinutes) {
        this.pollingIntervalMinutes = pollingIntervalMinutes;
    }

    public LocalDateTime getNextPolling() {
        return nextPolling;
    }

    public void setNextPolling(LocalDateTime nextPolling) {
        this.nextPolling = nextPolling;

    }
}
