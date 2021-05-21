package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Station extends Model {
    public String name;
    public double lat;
    public double lng;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    public String latestWeather;
    public double fahrenheit;
    public double celsius;
    public double windSpeedBFT;
    public int showPressure;
    public String windCompass;
    public double windChill;

    public double getMinTemperature;
    public double getMaxTemperature;
    public double getMinWind;
    public double getMaxWind;
    public double getMinPressure;
    public double getMaxPressure;
    public String weatherToHTML;
    public double getTempOne;
    public double getTempTwo;
    public String tempTrend;
    public double getWindOne;
    public double getWindTwo;
    public String windTrend;
    public double getPressureOne;
    public double getPressureTwo;
    public String pressureTrend;


    public Station(String name) {
        this.name = name;
    }
    public Station(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String convertToLatestWeather(int code) {
        if (code <= 100) return "Clear";
        else if (code == 200) return "Partial Clouds";
        else if (code == 300) return "Cloudy";
        else if (code == 400) return "Light Showers";
        else if (code == 500) return "Heavy Showers";
        else if (code == 600) return "Rain";
        else if (code == 700) return "Snow";
        else if (code == 800) return "Thunder";
        return "invalid code";
    }

    public String weatherToHTML(int code) {
        if (code <= 100) return "yellow cloud sun icon";
        else if (code == 200) return "grey cloud meatball icon";
        else if (code == 300) return "grey cloud icon";
        else if (code == 400) return "blue cloud rain icon";
        else if (code == 500) return "blue cloud showers heavy icon";
        else if (code == 600) return "blue cloud showers heavy icon";
        else if (code == 700) return "snowflake outline icon";
        else if (code == 800) return "yellow bolt icon";
        return "pink rainbow icon";
    }

    public double convertToFahrenheit(double temperature) {
        return ((temperature * 9/5) + 32);
    }

    public double convertToWindSpeedBFT(double windSpeed) {
        if (windSpeed == 1) return 0;
        else if (windSpeed >= 1 && windSpeed <=  5) return 1;
        else if (windSpeed >= 6 && windSpeed <= 11) return 2;
        else if (windSpeed >= 12 && windSpeed <= 19) return 3;
        else if (windSpeed >= 20 && windSpeed <= 28) return 4;
        else if (windSpeed >= 29 && windSpeed <= 38) return 5;
        else if (windSpeed >= 39 && windSpeed <= 49) return 6;
        else if (windSpeed >= 50 && windSpeed <= 61) return 7;
        else if (windSpeed >= 62 && windSpeed <= 74) return 8;
        else if (windSpeed >= 75 && windSpeed <= 88) return 9;
        else if (windSpeed >= 89 && windSpeed <= 102) return 10;
        else if (windSpeed >= 103 && windSpeed <= 117) return 11;
        else return 0;
    }

    public int showPressure(int pressure) {
        return pressure;
    }


    public String showWindCompass(int windDirection) {
        if (windDirection >= 0 && windDirection <= 11.25) return "North";
        else if (windDirection >= 11.25 && windDirection <=  33.75) return "North North East";
        else if (windDirection >= 33.75 && windDirection <= 56.25) return "North East";
        else if (windDirection >= 56.25 && windDirection <= 78.75) return "East North East";
        else if (windDirection >= 78.75 && windDirection <= 101.25) return "East";
        else if (windDirection >= 101.25 && windDirection <= 123.75) return "East South East";
        else if (windDirection >= 123.75 && windDirection <= 146.25) return "South East";
        else if (windDirection >= 146.25 && windDirection <= 168.75) return "South South East";
        else if (windDirection >= 168.75 && windDirection <= 191.25) return "South";
        else if (windDirection >= 191.25 && windDirection <= 213.75) return "South South West";
        else if (windDirection >= 213.75 && windDirection <= 236.25) return "South West";
        else if (windDirection >= 236.25 && windDirection <= 258.75) return "West South West";
        else if (windDirection >= 258.75 && windDirection <= 281.25) return "West";
        else if (windDirection >= 281.25 && windDirection <= 303.75) return "West North West";
        else if (windDirection >= 303.75 && windDirection <= 326.25) return "North West";
        else if (windDirection >= 326.25 && windDirection <= 348.75) return "North North West";
        return "invalid code";
    }

    public String getTempTrend(double getTempOne, double getTempTwo) {
        if (getTempOne < getTempTwo) return "big green sort up icon";
        else if (getTempTwo < getTempOne) return "big red sort down icon";
        return "big yellow sort icon";
    }

    public String getWindTrend(double getWindOne, double getWindTwo) {
        if (getWindOne < getWindTwo) return "big green sort up icon";
        else if (getWindTwo < getWindOne) return "big red sort down icon";
        return "big yellow sort icon";
    }

    public String getPressureTrend(double getPressureOne, double getPressureTwo) {
        if (getPressureOne < getPressureTwo) return "big green sort up icon";
        else if (getPressureTwo < getPressureOne) return "big red sort down icon";
        return "big yellow sort icon";
    }

    public double showWindChill(double windSpeed, double temperature) {
        double number;
        number = 13.12 + 0.6215*temperature -  11.37*Math.pow(windSpeed, 0.16) + 0.3965*temperature*Math.pow(windSpeed, 0.16);
        return (double)Math.round(number * 100d) / 100d;
    }
}


