package org.atiuleneva.dz6weather;

import jdk.nashorn.internal.parser.JSONParser;
import okhttp3.*;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/forecast?id=498817&appid=297629d730a13f4f4e07feba0c86b847&units=metric")
                .build();

        Response response = client.newCall(request).execute();

        String body = response.body().string();
        System.out.println(body);
    }
}
