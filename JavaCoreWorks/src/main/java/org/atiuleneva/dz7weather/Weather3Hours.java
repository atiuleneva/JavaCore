package org.atiuleneva.dz7weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Weather3Hours {
    private String dt_txt;
    private MainForecast main;
    private Wind wind;

    @JsonGetter("dt_txt")
    public String getDt_txt() {
        return dt_txt;
    }

    @JsonSetter("dt_txt")
    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    @JsonGetter("main")
    public MainForecast getMain() {
        return main;
    }

    @JsonSetter("main")
    public void setMain(MainForecast main) {
        this.main = main;
    }

    @JsonGetter("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonSetter("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
