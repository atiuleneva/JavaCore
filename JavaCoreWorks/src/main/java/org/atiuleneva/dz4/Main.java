package org.atiuleneva.dz4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        task1();
        task2();
    }

    public static void task1(){
        ArrayList<String> wordList = new ArrayList<String>();
        wordList.add("World");
        wordList.add("Sun");
        wordList.add("Sea");
        wordList.add("Rain");
        wordList.add("Dog");
        wordList.add("Cat");
        wordList.add("Mouse");
        wordList.add("World");
        wordList.add("Java");
        wordList.add("Sea");
        wordList.add("World");
        wordList.add("Winter");
        wordList.add("Wind");

        HashMap<String,Integer> uniqueWords = new HashMap<String, Integer>();
        for (int i=0; i<wordList.size(); i++){
            String word = wordList.get(i);
            if (uniqueWords.containsKey(word)) {
                uniqueWords.put(word, uniqueWords.get(word) + 1);
            }
            else {
                uniqueWords.put(word, 1);
            }
        }

        for (String key: uniqueWords.keySet()) {
            System.out.println("Слово " + key + " встречается " + uniqueWords.get(key) + " раз(а)");
        }
        System.out.println("============================================");
    }



    public static void task2(){

        Contacts contacts = new Contacts();
        contacts.addContact("Андреев","81111111111");
        contacts.addContact("Андреев","81111111121");
        contacts.addContact("Андреев","81111111131");
        contacts.addContact("Иванов","81121111141");
        contacts.addContact("Иванов","81111311171");
        contacts.addContact("Иванов","81411111119");
        contacts.addContact("Иванов","82111111221");
        contacts.addContact("Петров","81511111117");
        contacts.addContact("Петров","81117111111");
        contacts.addContact("Сидоров","81119111111");

        contacts.printNames();
        System.out.println("Для поиска телефона введите фамилию!");
        String name = scanner.nextLine();
        ArrayList<String> phones = contacts.getPhones(name);

        for (String phone:phones){
            System.out.println(name + " " + phone);
        }


    }

}
