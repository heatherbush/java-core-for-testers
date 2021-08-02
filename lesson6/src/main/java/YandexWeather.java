import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class YandexWeather {

    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

//        Пробовала отправить запрос с url из этого конструктора, но с ним возвращается false
//        Так и не смогла разобраться почему =/
//        HttpUrl urlSpb = new HttpUrl.Builder()
//                .scheme("https")
//                .host("api.weather.yandex.ru")
//                .addPathSegment("v2/forecast")
//                .addQueryParameter("lat","59.939099")
//                .addQueryParameter("lon", "30.315877")
//                .addQueryParameter("ru_RU", "5")
//                .build();

        String urlSpb = new String("https://api.weather.yandex.ru/v2/forecast?" +
                "lat=59.939099&lon=30.315877&lang=ru_RU&limit=5");

        String apiKey = new String("81cb30fb-23f6-43ad-9259-6ca5b5b65e02");

        Request requestSpbWeather = new Request.Builder()
                .url(urlSpb)
                .addHeader("X-Yandex-API-Key", apiKey)
                .build();

        Response responseSpbWeather = okHttpClient.newCall(requestSpbWeather).execute();

        System.out.println(responseSpbWeather.isSuccessful());

        String responseBodyWithToken = responseSpbWeather.body().string();

        System.out.println(responseBodyWithToken);

    }
}
