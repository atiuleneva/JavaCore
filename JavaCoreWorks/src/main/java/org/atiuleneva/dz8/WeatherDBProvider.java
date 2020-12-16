package org.atiuleneva.dz8;

import org.atiuleneva.dz7weather.MainForecast;
import org.atiuleneva.dz7weather.Weather3Hours;
import org.atiuleneva.dz7weather.Wind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

public class WeatherDBProvider {
    public void addWeatherRecords(ArrayList<Weather3Hours> list, String city){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weather?&serverTimezone=UTC", "root", "guCVRkjbyJjL4j");

            Statement stmt = connection.createStatement();
            String cityIdQuery = String.format("SELECT idcity FROM city WHERE name='%s'", city);
            ResultSet rs = stmt.executeQuery(cityIdQuery);

            rs.next();
            int cityId = rs.getInt("idcity");

            Statement stmtAddWeather = connection.createStatement();
            for (Weather3Hours w:list) {
                String weatherQuery = String.format(Locale.US, "INSERT INTO weather (idcity, date, t, t_feels, wind_dir, speed) VALUES (%d, '%s', %.2f, %.2f, '%s', %.2f) ON DUPLICATE KEY UPDATE t=%.2f, t_feels=%.2f, wind_dir='%s', speed=%.2f;\r\n",
                        cityId,
                        w.getDt_txt(),
                        w.getMain().getTemp(),
                        w.getMain().getFeels_like(),
                        w.getWind().getDirection(),
                        w.getWind().getSpeed(),
                        w.getMain().getTemp(),
                        w.getMain().getFeels_like(),
                        w.getWind().getDirection(),
                        w.getWind().getSpeed()
                );

                stmtAddWeather.addBatch(weatherQuery);
            }
            stmtAddWeather.executeBatch();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Weather3Hours> getWeatherRecords(String city){
        ArrayList<Weather3Hours> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weather?&serverTimezone=UTC", "root", "guCVRkjbyJjL4j");

            Statement stmt = connection.createStatement();
            String query = String.format("SELECT w.idcity,date,t,t_feels,wind_dir,speed,c.name FROM weather AS w, city AS c WHERE c.name='%s' AND w.idcity=c.idcity;", city);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Weather3Hours w3h = new Weather3Hours();
                w3h.setDt_txt(rs.getString("date"));
                MainForecast mf = new MainForecast();
                mf.setTemp(rs.getFloat("t"));
                mf.setFeels_like(rs.getFloat("t_feels"));
                Wind wind = new Wind();
                wind.setSpeed(rs.getFloat("speed"));

                w3h.setMain(mf);
                w3h.setWind(wind);

                list.add(w3h);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    
}
