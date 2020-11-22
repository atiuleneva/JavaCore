package org.atiulenevadz1;

public class Wall extends Barrier {
    private int height;

    public Wall(int height){
        this.height = height;
    }
    @Override
    public boolean Overcome(Object runner) {
        if (runner instanceof Robot) {
            Robot robot = (Robot) runner;
            return robot.jump(height);
        }
        else if (runner instanceof Human) {
            Human human = (Human) runner;
            return human.jump(height);
        }
        else if (runner instanceof Cat) {
            Cat cat = (Cat)runner;
            return cat.jump(height);
        }
        return false;
    }
}
