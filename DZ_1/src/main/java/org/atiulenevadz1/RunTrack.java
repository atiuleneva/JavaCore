package org.atiulenevadz1;

public class RunTrack extends Barrier {
    private int length;

    public RunTrack(int length){
        this.length = length;
    }

    @Override
    public boolean Overcome(Object runner) {
        if (runner instanceof Robot) {
            Robot robot = (Robot) runner;
            return robot.run(length);
        }
        else if (runner instanceof Human) {
            Human human = (Human) runner;
            return human.run(length);
        }
        else if (runner instanceof Cat) {
            Cat cat = (Cat)runner;
            return cat.run(length);
        }

        return false;
    }
}
