import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuWeather {
    public static void main(String[] args) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient();

        String apiKey = new String("zydfomCZj2mVPqtuZYMGM8TEyBBYVdj6");
        String location = new String("295212");
        String days = new String("5");

        String urlSpb = new String("http://dataservice.accuweather.com/forecasts/v1/daily/" + days
                + "day/" + location + "?apikey=" + apiKey + "&language=ru-ru");

        Request requestSpbWeather = new Request.Builder()
                .url(urlSpb)
                .build();

        Response responseSpbWeather = okHttpClient.newCall(requestSpbWeather).execute();

        System.out.println(responseSpbWeather.isSuccessful());

        String responseBodyWithToken = responseSpbWeather.body().string();

        System.out.println(responseBodyWithToken);
    }
}
