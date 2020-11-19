package org.atiulenevadz1;

public class Human {
    private final int MAX_RUN_DISTANCE = 5000;
    private final int MAX_HEIGHT_JUMP = 3;
    public boolean run(int length) {
        if (length > 0 && length <= MAX_RUN_DISTANCE){
            System.out.printf("Человек пробежал %d м.\r\n", length);
            return true;
        }
        else{
            System.out.printf("Человек не смог пробежать дистанцию %d м.\r\n", length);
            return false;
        }
    }

    public boolean jump(int height){
        if (height > 0 && height <= MAX_HEIGHT_JUMP){
            System.out.printf("Человек подпрыгнул на %d м.\r\n",height);
            return true;
        }
        else {
            System.out.printf("Человек не смог прыгнутьть %d м.\r\n", height);
            return false;
        }
    }
}
