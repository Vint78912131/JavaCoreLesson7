package YandexWeather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.*;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class OkHttpYandexWeather {
    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.weather.yandex.ru")
                .addPathSegment("v2")
                .addPathSegment("forecast")
                .addQueryParameter("lat", "55.8204")
                .addQueryParameter("lon", "37.3302")
                .addQueryParameter("lang", "ru_RU")
                .addQueryParameter("limit", "7")
                .addQueryParameter("hours", "false")
                .addQueryParameter("extra", "true")
                .build();

        Request request = new Request.Builder()
                .addHeader("X-Yandex-API-Key", "81402e9b-8fc1-4e4a-941b-3c101867ebcf")
                .url(url)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String body = response.body().string();
        ObjectMapper objectMapper = new ObjectMapper();
        String city = objectMapper.readTree(body).at("/geo_object/locality/name").asText();

        ArrayNode forcasts = (ArrayNode) objectMapper.readTree(body).at("/forecasts");

        ArrayList <WeatherResponse> ws = new ArrayList<>();

        System.out.println(city);
            for (int i = 0; i < forcasts.size(); i++) {
                JsonNode individualElement = forcasts.get(i);
                String date = individualElement.get("date").asText();
                System.out.println(date);
                String morningT = individualElement.get("parts").get("morning").get("temp_avg").asText();
                String morningC = individualElement.get("parts").get("morning").get("condition").asText();
                String dayT = individualElement.get("parts").get("day").get("temp_avg").asText();
                String dayC = individualElement.get("parts").get("day").get("condition").asText();
                String eveningT = individualElement.get("parts").get("evening").get("temp_avg").asText();
                String eveningC = individualElement.get("parts").get("evening").get("condition").asText();
                String nightT = individualElement.get("parts").get("night").get("temp_avg").asText();
                String nightC = individualElement.get("parts").get("night").get("condition").asText();
                System.out.println("morning: " + morningT);
                System.out.println("condition: " + morningC);
                System.out.println("day: " + dayT);
                System.out.println("condition: " + dayC);
                System.out.println("evening: " + eveningT);
                System.out.println("condition: " + eveningC);
                System.out.println("night: " + nightT);
                System.out.println("condition: " + nightC);
                System.out.println();
                ws.add(new WeatherResponse(date,city,dayC,Integer.parseInt(dayT)));
            }

        for (WeatherResponse o : ws) {
            String weatherF = objectMapper.writeValueAsString(o);
            System.out.println(weatherF);
        }
    }
}