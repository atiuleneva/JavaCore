package org.atiuleneva.dz7weather;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Wind {
    private double speed;
    private int deg;

    @JsonGetter("speed")
    public double getSpeed() {
        return speed;
    }

    @JsonSetter("speed")
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @JsonGetter("deg")
    public int getDeg() {
        return deg;
    }

    @JsonSetter("deg")
    public void setDeg(int deg) {
        this.deg = deg;
    }

    public String getDirection(){
        if (315 <= deg && deg <= 360 || 0 <= deg && deg <= 45){
            return "северный";
        }
        else if (45 <= deg &&  deg <= 135 ){
            return "восточный";
        }
        else if (135 <= deg && deg <= 225){
            return "южный";
        }
        else if(225 <= deg && deg <= 315){
            return "западный";
        }

        return "Unknown";
    }
}
