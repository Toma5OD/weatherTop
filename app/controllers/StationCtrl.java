package controllers;

import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;
import utils.StationAnalytics;

import java.util.Date;

import static java.lang.System.currentTimeMillis;

public class StationCtrl extends Controller
{
  public static void index(Long id)
  {
    Station station = Station.findById(id);
    if(station.readings.size() > 0) {
      Reading reading = station.readings.get((station.readings.size()) - 1);
      station.latestWeather = station.convertToLatestWeather(reading.code);
      station.weatherToHTML = station.weatherToHTML(reading.code);
      station.fahrenheit = station.convertToFahrenheit(reading.temperature);
      station.celsius = reading.temperature;
      station.windSpeedBFT = station.convertToWindSpeedBFT(reading.windSpeed);
      station.showPressure = station.showPressure(reading.pressure);
      station.windCompass = station.showWindCompass(reading.windDirection);
      station.windChill = station.showWindChill(reading.windSpeed, reading.temperature);
      station.getMinTemperature = StationAnalytics.getMinTemperature(station.readings).temperature;
      station.getMaxTemperature = StationAnalytics.getMaxTemperature(station.readings).temperature;
      station.getMinWind = StationAnalytics.getMinWind(station.readings).windSpeed;
      station.getMaxWind = StationAnalytics.getMaxWind(station.readings).windSpeed;
      station.getMinPressure = StationAnalytics.getMinPressure(station.readings).pressure;
      station.getMaxPressure = StationAnalytics.getMaxPressure(station.readings).pressure;
      if(station.readings.size() > 1) {
        station.getTempOne = StationAnalytics.getTempOne(station.readings).temperature;
        station.getTempTwo = StationAnalytics.getTempTwo(station.readings).temperature;
        station.tempTrend = station.getTempTrend(station.getTempOne, station.getTempTwo);
        station.getWindOne = StationAnalytics.getWindOne(station.readings).windSpeed;
        station.getWindTwo = StationAnalytics.getWindTwo(station.readings).windSpeed;
        station.windTrend = station.getTempTrend(station.getWindOne, station.getWindTwo);
        station.getPressureOne = StationAnalytics.getPressureOne(station.readings).pressure;
        station.getPressureTwo = StationAnalytics.getPressureTwo(station.readings).pressure;
        station.pressureTrend = station.getTempTrend(station.getPressureOne, station.getPressureTwo);
      }
    }
    Logger.info ("Station id = " + id);
    render("station.html", station);
  }

  public static void deleteReading (Long id, Long readingid)
  {
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    Logger.info ("Removing" + reading.code);
    station.readings.remove(reading);
    station.save();
    reading.delete();
    redirect ("/dashboard");
    //Using redirect to reload updated page. Perhaps render is preffered??
  }

  public static void addReading(Long id, int code, double temperature, double windSpeed, int windDirection, int pressure, double fahrenheit, double celsius, double windChill)
  {
    Station station = Station.findById(id);
    Date date = new Date(currentTimeMillis());
    Reading reading = new Reading(date, code, temperature, windSpeed, windDirection, pressure, fahrenheit, celsius, windChill);
    station.readings.add(reading);
    station.save();
    index(station.id);
    render("station.html", station);
  }
}
