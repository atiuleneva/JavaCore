package org.atiuleneva.dz3;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // 1 задание

        String [] mamArr ={"мама","мыла","раму"};
        changePlaces(mamArr);

        // 2 задание
        Box<Apple> appleBox = new Box<Apple>();
        Box<Apple> appleBox2 = new Box<Apple>();
        Box <Orange> orangeBox = new Box<Orange>();

        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());

        appleBox2.shift(appleBox,2);

        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());

        System.out.printf("Коробка с яблоками весит: %.2f \r\n", appleBox.getWeight());
        System.out.printf("Коробка с яблоками №2 весит: %.2f \r\n", appleBox2.getWeight());
        System.out.printf("Коробка с апельсинами весит: %.2f \r\n", orangeBox.getWeight());

        if (appleBox.compareTo(orangeBox) == 0) {
            System.out.println("Коробки одинакового веса");
        }else if (appleBox.compareTo(orangeBox)<0){
            System.out.println("Коробка яблок весит меньше чем коробка апельсинов");
        }else if (appleBox.compareTo(orangeBox)>0){
            System.out.println("Коробка яблок весит больше коробки апельсинов");
        }

    }

    public static void changePlaces(String [] mamArr) {

        for (int i=0; i<mamArr.length; i++){
           System.out.print(mamArr[i] + " ");
        }
        System.out.println();

        String a = mamArr[0];
        mamArr[0]=mamArr[2];
        mamArr[2] = a;

        for (int i=0; i<mamArr.length; i++){
            System.out.print(mamArr[i] + " ");
        }

        System.out.println();

        System.out.println();
    }

}


