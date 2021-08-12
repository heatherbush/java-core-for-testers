import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "zydfomCZj2mVPqtuZYMGM8TEyBBYVdj6";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final String LANGUAGE = "language";
    private static final String RU_LANGUAGE = "ru-ru";
    private static final String UNIT_TYPE = "metric";
    private static final String CELSIUS = "true";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    @Override
    public void getWeather(String selectedCity, Period period, String base) throws IOException, SQLException {
        switch (period) {
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(UNIT_TYPE, CELSIUS)
                        .addQueryParameter(LANGUAGE, RU_LANGUAGE)
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = oneDayForecastResponse.body().string();
                //TODO: сделать человекочитаемый вывод погоды. Выбрать параметры для вывода на свое усмотрение

                String dirtyDateForOneDay = objectMapper.readTree(weatherResponse).get("Headline").findValue("EffectiveDate").asText();
                String dateForOneDay  = dirtyDateForOneDay.split("T")[0];
                String generalForecast = objectMapper.readTree(weatherResponse).get("Headline").findValue("Text").asText();
                String temperatureMinimum = objectMapper.readTree(weatherResponse).get("DailyForecasts")
                        .findValue("Temperature").findValue("Minimum").findValue("Value").asText();
                String temperatureMaximum = objectMapper.readTree(weatherResponse).get("DailyForecasts")
                        .findValue("Temperature").findValue("Maximum").findValue("Value").asText();
                String forecastForTheDay = objectMapper.readTree(weatherResponse).get("DailyForecasts").findValue("Day")
                        .findValue("IconPhrase").asText();
                String forecastForTheNight = objectMapper.readTree(weatherResponse).get("DailyForecasts").findValue("Night")
                        .findValue("IconPhrase").asText();

                String weatherInTheCity = new String(
                        "Погода в городе " + selectedCity + "\n" +
                                "На текущую дату: " + dateForOneDay + "\n" +
                                "Общий прогноз: " + generalForecast + ".\n" +
                                "Температурный минимум: " + temperatureMinimum + "°C\n" +
                                "Температурный максимум: " + temperatureMaximum + "°C\n" +
                                "Днём: " + forecastForTheDay + ".\n" +
                                "Ночью: " + forecastForTheNight + ".\n"
                );

                dataBaseRepository.saveWeatherToDataBase(new Weather(selectedCity, dateForOneDay, generalForecast,
                        temperatureMinimum, temperatureMaximum, forecastForTheDay, forecastForTheNight));

                System.out.println(weatherInTheCity);

                if (base.equals("2")) {
                    DataBaseRepository.printDBWeather(1);
                }
                break;
            case FIVE_DAYS:
                //TODO*: реализовать вывод погоды на 5 дней
                HttpUrl httpUrlForFiveDays = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(UNIT_TYPE, CELSIUS)
                        .addQueryParameter(LANGUAGE, RU_LANGUAGE)
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request requestForFiveDays = new Request.Builder()
                        .url(httpUrlForFiveDays)
                        .build();

                Response fiveDaysForecastResponse = okHttpClient.newCall(requestForFiveDays).execute();
                String weatherResponseForFiveDays = fiveDaysForecastResponse.body().string();

                for (int i = 0; i < 5; i++) {
                    String dirtyDateForFiveDays = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts").get(i)
                            .findValue("Date").asText();
                    String dateForFiveDays  = dirtyDateForFiveDays.split("T")[0];
                    String forecastForFiveDays = objectMapper.readTree(weatherResponseForFiveDays).get("Headline")
                            .findValue("Text").asText();
                    String temperatureMinForFiveDays = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(i).findValue("Temperature").findValue("Minimum").findValue("Value").asText();
                    String temperatureMaxForFiveDays = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(i).findValue("Temperature").findValue("Maximum").findValue("Value").asText();
                    String dayForecastForFiveDays = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(i).findValue("Day").findValue("IconPhrase").asText();
                    String nightForecastForFiveDays = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(i).findValue("Night").findValue("IconPhrase").asText();

                    String weatherForFiveDays = new String(
                            "Погода в городе " + selectedCity +
                                    " на дату: " + dateForFiveDays + "\n" +
                                    "Температурный минимум: " + temperatureMinForFiveDays + "°C\n" +
                                    "Температурный максимум: " + temperatureMaxForFiveDays + "°C\n" +
                                    "Днём: " + dayForecastForFiveDays + ".\n" +
                                    "Ночью: " + nightForecastForFiveDays + ".\n\n"
                    );

                    dataBaseRepository.saveWeatherToDataBase(new Weather(selectedCity, dateForFiveDays, forecastForFiveDays, temperatureMinForFiveDays,
                            temperatureMaxForFiveDays, dayForecastForFiveDays, nightForecastForFiveDays));

                    System.out.println(weatherForFiveDays);


                }

                if (base.equals("2")) {
                    DataBaseRepository.printDBWeather(5);
                }
                break;
        }
    }

    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }
}
