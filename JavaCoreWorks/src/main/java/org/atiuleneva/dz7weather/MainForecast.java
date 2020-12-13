package org.atiuleneva.dz7weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MainForecast {
    private double temp;
    private double feels_like;

    @JsonGetter("feels_like")
    public double getFeels_like() {
        return feels_like;
    }

    @JsonSetter("feels_like")
    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    @JsonGetter("temp")
    public double getTemp() {
        return temp;
    }

    @JsonSetter("temp")

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
