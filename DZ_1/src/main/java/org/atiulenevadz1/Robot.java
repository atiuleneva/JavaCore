package org.atiulenevadz1;

public class Robot {
    private final int MAX_RUN_DISTANCE = 20000;
    private final int MAX_HEIGHT_JUMP = 20;
    public boolean run(int length) {
        if (length > 0 && length <= MAX_RUN_DISTANCE){
            System.out.printf("Робот пробежал %d м.\r\n", length);
            return true;
        }
        else {
            System.out.printf("Рбот не смог  пробежать дистанцию %d м.\r\n", length);
            return false;
        }

    }

    public boolean jump(int height){
        if (height > 0 && height <= MAX_HEIGHT_JUMP){
            System.out.printf("Робот подпрыгнул на %d м.\r\n",height);
            return true;
        }
        else {
            System.out.printf("Робот не смог прыгнуть %d м.\r\n", height);
            return false;
        }

    }
}
