package org.atiuleneva.dz8;

import org.atiuleneva.dz7weather.Weather3Hours;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DBProvider {

    void addWeatherRecords(ArrayList<Weather3Hours> list, String city);

    ArrayList<Weather3Hours> getWeatherRecords(String city);
}
