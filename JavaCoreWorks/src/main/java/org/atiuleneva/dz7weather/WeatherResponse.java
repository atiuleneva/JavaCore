package org.atiuleneva.dz7weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;

public class WeatherResponse {
    private ArrayList<Weather3Hours> list;

    @JsonGetter("list")
    public ArrayList<Weather3Hours> getList() {
        return list;
    }

    @JsonSetter("list")
    public void setList(ArrayList<Weather3Hours> list) {
        this.list = list;
    }
}
