package controllers;

import java.util.List;

import models.Member;
import models.Station;
import models.Reading;
import utils.StationAnalytics;
import play.Logger;
import play.mvc.Controller;


public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();

    List<Station> stations = Station.findAll();
    for(Station station : stations) {
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
    }
    render ("dashboard.html", stations, member);
  }

  public static void deleteStation (Long id)
  {
    Station station = Station.findById(id);
    Logger.info ("Removing" + station.name);
    station.delete();
    redirect ("/dashboard");
  }

  public static void addStation (String name, double lat, double lng)
  {
    Logger.info ("Adding a new station called " + name);
    List<Station> stations = Station.findAll();
    Station station = new Station (name, lat, lng);
    stations.add(station);
    station.save();
    redirect ("/dashboard");
  }
}

