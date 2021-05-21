package utils;

import models.Reading;

import java.util.List;

public class StationAnalytics {
    public static Reading getMinTemperature(List<Reading> readings) {
        Reading minReading = null;
        if (readings.size() > 0) {
            minReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature < minReading.temperature) {
                    minReading = reading;
                }
            }
        }
        return minReading;
    }

    public static Reading getMaxTemperature(List<Reading> readings) {
        Reading maxReading = null;
        if (readings.size() > 0) {
            maxReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature > maxReading.temperature) {
                    maxReading = reading;
                }
            }
        }
        return maxReading;
    }

    public static Reading getMinWind(List<Reading> readings) {
        Reading minReading = null;
        if (readings.size() > 0) {
            minReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed < minReading.windSpeed) {
                    minReading = reading;
                }
            }
        }
        return minReading;
    }

    public static Reading getMaxWind(List<Reading> readings) {
        Reading maxReading = null;
        if (readings.size() > 0) {
            maxReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed > maxReading.windSpeed) {
                    maxReading = reading;
                }
            }
        }
        return maxReading;
    }

    public static Reading getMinPressure(List<Reading> readings) {
        Reading minReading = null;
        if (readings.size() > 0) {
            minReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure < minReading.pressure) {
                    minReading = reading;
                }
            }
        }
        return minReading;
    }

    public static Reading getMaxPressure(List<Reading> readings) {
        Reading maxReading = null;
        if (readings.size() > 0) {
            maxReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure > maxReading.pressure) {
                    maxReading = reading;
                }
            }
        }
        return maxReading;
    }

    public static Reading getTempOne(List<Reading> readings) {
        Reading tempTrend = null;
        if (readings.size() > 1) {
            tempTrend = readings.get(readings.size() - 2);
        }
        return tempTrend;
    }

    public static Reading getTempTwo(List<Reading> readings) {
        Reading tempTrend = null;
        if (readings.size() > 1) {
            tempTrend = readings.get(readings.size() - 1);
        }
        return tempTrend;
    }

    public static Reading getWindOne(List<Reading> readings) {
        Reading windTrend = null;
        if (readings.size() > 1) {
            windTrend = readings.get(readings.size() - 2);
        }
        return windTrend;
    }

    public static Reading getWindTwo(List<Reading> readings) {
        Reading windTrend = null;
        if (readings.size() > 1) {
            windTrend = readings.get(readings.size() - 1);
        }
        return windTrend;
    }

    public static Reading getPressureOne(List<Reading> readings) {
        Reading pressureTrend = null;
        if (readings.size() > 1) {
            pressureTrend = readings.get(readings.size() - 2);
        }
        return pressureTrend;
    }

    public static Reading getPressureTwo(List<Reading> readings) {
        Reading pressureTrend = null;
        if (readings.size() > 1) {
            pressureTrend = readings.get(readings.size() - 1);
        }
        return pressureTrend;
    }

}

