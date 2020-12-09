package org.atiuleneva.dz7weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // request weather from service
        OkHttpClient client = new OkHttpClient();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите город на английском языке (например: Nizhny Novgorod, Saint Petersburg, Orel, Perm etc.)");
        String city = scanner.nextLine();

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
    }
}