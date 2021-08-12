package entity;

public class Weather {
    private String city;
    private String date;
    private String generalForecast;
    private String temperatureMin;
    private String temperatureMax;
    private String dayForecast;
    private String nightForecast;

    public Weather(String city, String date, String generalForecast, String temperatureMin, String temperatureMax,
                   String dayForecast, String nightForecast) {
        this.city = city;
        this.date = date;
        this.generalForecast = generalForecast;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.dayForecast = dayForecast;
        this.nightForecast = nightForecast;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGeneralForecast() {
        return generalForecast;
    }

    public void setGeneralForecast(String generalForecast) {
        this.generalForecast = generalForecast;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getDayForecast() {
        return dayForecast;
    }

    public void setDayForecast(String dayForecast) {
        this.dayForecast = dayForecast;
    }

    public String getNightForecast() {
        return nightForecast;
    }

    public void setNightForecast(String nightForecast) {
        this.nightForecast = nightForecast;
    }
}
