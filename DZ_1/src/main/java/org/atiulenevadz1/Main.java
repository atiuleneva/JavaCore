package org.atiulenevadz1;

public class Main {

    public static void main(String[] args) {
        Object [] runners = {
            new Cat(),
            new Robot(),
            new Human(),
        };

        Barrier [] barriers = {
          new RunTrack(200),
          new Wall(1),
          new RunTrack(2000),
          new Wall(2),
          new RunTrack(10000),
          new Wall(7)
        };

        for (int i=0; i< runners.length; i++){
            Object r = runners[i];
            for (int j=0; j< barriers.length; j++){
                if (barriers[j].Overcome(r) == false)
                    break;
            }
        }
    }
}
