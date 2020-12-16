package org.atiuleneva.dz8;

import org.atiuleneva.dz7weather.MainForecast;
import org.atiuleneva.dz7weather.Weather3Hours;
import org.atiuleneva.dz7weather.Wind;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class WeatherDBProviderLite implements DBProvider {
    public void addWeatherRecords(ArrayList<Weather3Hours> list, String city) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:dz8db.db");
            createDBIfNotExist(connection);

            Statement stmt = connection.createStatement();
            String cityIdQuery = String.format("SELECT idcity FROM city WHERE name='%s' COLLATE NOCASE", city);
            ResultSet rs = stmt.executeQuery(cityIdQuery);

            rs.next();
            int cityId = rs.getInt("idcity");

            Statement stmtAddWeather = connection.createStatement();
            for (Weather3Hours w:list) {
                String weatherQuery = String.format(Locale.US, "INSERT INTO weather (idcity, date, t, t_feels, speed) VALUES (%d, '%s', %.2f, %.2f, %.2f) ON CONFLICT(idcity, date) DO UPDATE SET t=%.2f, t_feels=%.2f, speed=%.2f;\r\n",
                        cityId,
                        w.getDt_txt(),
                        w.getMain().getTemp(),
                        w.getMain().getFeels_like(),
                        w.getWind().getSpeed(),
                        w.getMain().getTemp(),
                        w.getMain().getFeels_like(),
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
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:dz8db.db");
            createDBIfNotExist(connection);

            Statement stmt = connection.createStatement();
            String query = String.format("SELECT w.idcity,date,t,t_feels,speed,c.name FROM weather AS w, city AS c WHERE c.name='%s' COLLATE NOCASE AND w.idcity=c.idcity;", city);
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

    public void createDBIfNotExist(Connection connection) throws SQLException {
        Statement stmtCity = connection.createStatement();
        stmtCity.executeUpdate("CREATE TABLE IF NOT EXISTS city (idcity INTEGER PRIMARY KEY NOT NULL UNIQUE, name VARCHAR(256) NOT NULL)" );
        Statement stmtAddCities = connection.createStatement();
        stmtAddCities.addBatch("INSERT INTO city (idcity, name) VALUES (498817, \"Saint Petersburg\") ON CONFLICT(idcity) DO UPDATE SET name=\"Saint Petersburg\";");
        stmtAddCities.addBatch("INSERT INTO city (idcity, name) VALUES (499099, \"Samara\") ON CONFLICT(idcity) DO UPDATE SET name=\"Samara\";");
        stmtAddCities.addBatch("INSERT INTO city (idcity, name) VALUES (511196, \"Perm\") ON CONFLICT(idcity) DO UPDATE SET name=\"Perm\";");
        stmtAddCities.addBatch("INSERT INTO city (idcity, name) VALUES (520555, \"Nizhniy Novgorod\") ON CONFLICT(idcity) DO UPDATE SET name=\"Nizhniy Novgorod\";");
        stmtAddCities.addBatch("INSERT INTO city (idcity, name) VALUES (524894, \"Moscow\") ON CONFLICT(idcity) DO UPDATE SET name=\"Moscow\";");
        stmtAddCities.addBatch("INSERT INTO city (idcity, name) VALUES (658225, \"Helsinki\") ON CONFLICT(idcity) DO UPDATE SET name=\"Helsinki\";");
        stmtAddCities.addBatch("INSERT INTO city (idcity, name) VALUES (706483, \"Kharkiv\") ON CONFLICT(idcity) DO UPDATE SET name=\"Kharkiv\";");
        stmtAddCities.addBatch("INSERT INTO city (idcity, name) VALUES (2013364, \"Vladimir\") ON CONFLICT(idcity) DO UPDATE SET name=\"Vladimir\";");
        stmtAddCities.addBatch("INSERT INTO city (idcity, name) VALUES (2618425, \"Copenhagen\") ON CONFLICT(idcity) DO UPDATE SET name=\"Copenhagen\";");
        stmtAddCities.addBatch("INSERT INTO city (idcity, name) VALUES (3067696, \"Prague\") ON CONFLICT(idcity) DO UPDATE SET name=\"Prague\";");
        stmtAddCities.executeBatch();

        Statement stmtWeather = connection.createStatement();
        stmtWeather.executeUpdate("CREATE TABLE IF NOT EXISTS weather (idcity INTEGER NOT NULL, date VARCHAR(45) NOT NULL, t FLOAT NOT NULL , t_feels FLOAT NOT NULL, speed FLOAT NOT NULL, PRIMARY KEY(idcity, date))" );
    }

}
