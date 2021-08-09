import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "Hmt2LZpNplRcg8tWUTHdLtAsQANYCov2";
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

    @Override
    public void getWeather(String selectedCity, Period period) throws IOException {
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
                        "Погода в городе " + selectedCity + ":\n" +
                                "Общий прогноз: " + generalForecast + ".\n" +
                                "Температурный минимум: " + temperatureMinimum + "°C\n" +
                                "Температурный максимум: " + temperatureMaximum + "°C\n" +
                                "Днём: " + forecastForTheDay + ".\n" +
                                "Ночью: " + forecastForTheNight + ".\n"
                );

                System.out.println(weatherInTheCity);
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


                String dateFirst = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts").get(0)
                        .findValue("Date").asText();
                String temperatureMinFirst = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(0).findValue("Temperature").findValue("Minimum").findValue("Value").asText();
                String temperatureMaxFirst = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(0).findValue("Temperature").findValue("Maximum").findValue("Value").asText();
                String forecastForTheFirstDay = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(0).findValue("Day").findValue("IconPhrase").asText();
                String forecastForTheFirstNight = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(0).findValue("Night").findValue("IconPhrase").asText();

                String dateSecond = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts").get(1)
                        .findValue("Date").asText();
                String temperatureMinSecond = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(1).findValue("Temperature").findValue("Minimum").findValue("Value").asText();
                String temperatureMaxSecond = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(1).findValue("Temperature").findValue("Maximum").findValue("Value").asText();
                String forecastForTheSecondDay = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(1).findValue("Day").findValue("IconPhrase").asText();
                String forecastForTheSecondNight = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(1).findValue("Night").findValue("IconPhrase").asText();

                String dateThird = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts").get(2)
                        .findValue("Date").asText();
                String temperatureMinThird = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(2).findValue("Temperature").findValue("Minimum").findValue("Value").asText();
                String temperatureMaxThird = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(2).findValue("Temperature").findValue("Maximum").findValue("Value").asText();
                String forecastForTheThirdDay = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(2).findValue("Day").findValue("IconPhrase").asText();
                String forecastForTheThirdNight = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(2).findValue("Night").findValue("IconPhrase").asText();

                String dateFourth = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts").get(3)
                        .findValue("Date").asText();
                String temperatureMinFourth = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(3).findValue("Temperature").findValue("Minimum").findValue("Value").asText();
                String temperatureMaxFourth = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(3).findValue("Temperature").findValue("Maximum").findValue("Value").asText();
                String forecastForTheFourthDay = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(3).findValue("Day").findValue("IconPhrase").asText();
                String forecastForTheFourthNight = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(3).findValue("Night").findValue("IconPhrase").asText();

                String dateFifth = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts").get(4)
                        .findValue("Date").asText();
                String temperatureMinFifth = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(4).findValue("Temperature").findValue("Minimum").findValue("Value").asText();
                String temperatureMaxFifth = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(4).findValue("Temperature").findValue("Maximum").findValue("Value").asText();
                String forecastForTheFifthDay = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(4).findValue("Day").findValue("IconPhrase").asText();
                String forecastForTheFifthNight = objectMapper.readTree(weatherResponseForFiveDays).get("DailyForecasts")
                        .get(4).findValue("Night").findValue("IconPhrase").asText();

                String weatherForFiveDays = new String(
                        "Погода в городе " + selectedCity + ":\n\n" +
                                "На дату: " + dateFirst + "\n" +
                                "Температурный минимум: " + temperatureMinFirst + "°C\n" +
                                "Температурный максимум: " + temperatureMaxFirst + "°C\n" +
                                "Днём: " + forecastForTheFirstDay + ".\n" +
                                "Ночью: " + forecastForTheFirstNight + ".\n\n" +

                                "На дату: " + dateSecond + "\n" +
                                "Температурный минимум: " + temperatureMinSecond + "°C\n" +
                                "Температурный максимум: " + temperatureMaxSecond + "°C\n" +
                                "Днём: " + forecastForTheSecondDay + ".\n" +
                                "Ночью: " + forecastForTheSecondNight + ".\n\n" +

                                "На дату: " + dateThird + "\n" +
                                "Температурный минимум: " + temperatureMinThird + "°C\n" +
                                "Температурный максимум: " + temperatureMaxThird + "°C\n" +
                                "Днём: " + forecastForTheThirdDay + ".\n" +
                                "Ночью: " + forecastForTheThirdNight + ".\n\n" +

                                "На дату: " + dateFourth + "\n" +
                                "Температурный минимум: " + temperatureMinFourth + "°C\n" +
                                "Температурный максимум: " + temperatureMaxFourth + "°C\n" +
                                "Днём: " + forecastForTheFourthDay + ".\n" +
                                "Ночью: " + forecastForTheFourthNight + ".\n\n" +

                                "На дату: " + dateFifth + "\n" +
                                "Температурный минимум: " + temperatureMinFifth + "°C\n" +
                                "Температурный максимум: " + temperatureMaxFifth + "°C\n" +
                                "Днём: " + forecastForTheFifthDay + ".\n" +
                                "Ночью: " + forecastForTheFifthNight + ".\n\n"
                        );

                System.out.println(weatherForFiveDays);
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