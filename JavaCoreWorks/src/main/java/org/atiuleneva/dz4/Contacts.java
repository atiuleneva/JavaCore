package org.atiuleneva.dz4;

import java.util.ArrayList;
import java.util.HashMap;

public class Contacts {

    HashMap<String,String> contacts = new HashMap<String, String>();

    public void addContact(String name, String phone){
        contacts.put(phone,name);
    }

    public ArrayList<String> getPhones(String name){
        ArrayList<String> phones = new ArrayList<String>();

        for (String key:contacts.keySet()){
            String nm = contacts.get(key);
            if (nm.equalsIgnoreCase(name)){
                phones.add(key);
            }
        }

        return phones;
    }

    public void printNames(){
        System.out.println("Список имен в контактной книге");
        for (String key:contacts.keySet()) {
            System.out.println(contacts.get(key));
        }
    }

}
