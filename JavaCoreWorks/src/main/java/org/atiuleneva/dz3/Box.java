package org.atiuleneva.dz3;

import java.util.ArrayList;

public class Box <T extends Fruit> implements Comparable<Box<? extends Fruit>> {
    ArrayList<T> fruitBox;

    public Box(){
        fruitBox = new ArrayList<T>();
    }

    public void addFruit(T fruit){
        fruitBox.add(fruit);
    }

    public float getWeight(){
        int numFruits = fruitBox.size();
        T fruit = fruitBox.get(0);
        float weight = fruit.getFruitWeight();

        return weight * numFruits;
    }

    public int compareTo(Box<? extends Fruit> o) {
        if (this.getWeight() == o.getWeight()) {
            return 0;
        } else if (this.getWeight() < o.getWeight()) {
            return -1;
        } else {
            return 1;
        }
    }

    public void shift(Box<T> srcBox, int numFruits){
        for(int i=0; i<numFruits; i++){
            T fr = srcBox.fruitBox.get(0);
            this.fruitBox.add(fr);
            srcBox.fruitBox.remove(0);
        }
    }
}
