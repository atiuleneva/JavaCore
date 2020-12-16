package org.atiuleneva.dz8;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.atiuleneva.dz7weather.Weather3Hours;
import org.atiuleneva.dz7weather.WeatherResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите город на английском языке (например: Nizhniy Novgorod, Saint Petersburg, Perm etc.)");
        String city = scanner.nextLine();

        // чтение из MySQL DB
        //WeatherDBProvider provider = new WeatherDBProvider();
        WeatherDBProviderLite provider = new WeatherDBProviderLite();
        ArrayList<Weather3Hours> weatherFromDB = provider.getWeatherRecords(city);
        System.out.println("ПОГОДА ИЗ ЛОКАЛЬНОЙ БАЗЫ ДАННЫХ:");
        for (Weather3Hours w:weatherFromDB) {
            System.out.printf("В городе %s на %s ожидается %.2f ℃, ощущается как %.2f ℃, скорость ветра %d м/c\r\n",
                    city,
                    w.getDt_txt(),
                    w.getMain().getTemp(),
                    w.getMain().getFeels_like(),
                    Math.round(w.getWind().getSpeed())
            );
        }

        // Запрос на сервис погоды
        System.out.println("=======================================");
        System.out.println("ПОГОДА ИЗ СЕРВИСА ПОГОДЫ OPENWEATHERMAP:");
        String weatherUrl = String.format("https://api.openweathermap.org/data/2.5/forecast?q=%s&appid=297629d730a13f4f4e07feba0c86b847&units=metric",
                city
        );

        Request request = new Request.Builder()
                .url(weatherUrl)
                .build();

        Response response = client.newCall(request).execute();

        String body = response.body().string();
        // System.out.println(body);

        // deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        WeatherResponse wp = objectMapper.readValue(body, WeatherResponse.class);

        ArrayList<Weather3Hours> list = wp.getList();
        for (Weather3Hours w:list) {
            System.out.printf("В городе %s на %s ожидается %.2f ℃, ощущается как %.2f ℃, ветер %s, скорость %d м/c\r\n",
                    city,
                    w.getDt_txt(),
                    w.getMain().getTemp(),
                    w.getMain().getFeels_like(),
                    w.getWind().getDirection(),
                    Math.round(w.getWind().getSpeed())
            );
        }

        // запись в MySQL DB
        provider.addWeatherRecords(list, city);
    }

}
