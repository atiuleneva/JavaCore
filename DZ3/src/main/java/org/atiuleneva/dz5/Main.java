package org.atiuleneva.dz5;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        saveToCsv();
        openCsv1();
        openCsv2();
    }

    public static void saveToCsv () throws IOException {
        ArrayList<Box> boxes = new ArrayList<Box>();
        boxes.add(new Box(100, 1000,10));
        boxes.add(new Box(150, 1500, 15));
        boxes.add(new Box(200, 2000, 20));

        byte[] outData = "Weight;Quantity;Price;\r\n".getBytes();
        try (FileOutputStream csvFile = new FileOutputStream("boxes.csv")) {
            csvFile.write(outData);

            for (Box box: boxes) {

                String row = Integer.toString(box.weight) + ";" +
                        Integer.toString(box.quantity) + ";" +
                        Integer.toString(box.price) + ";\r\n";

                csvFile.write(row.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openCsv1 () throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("boxes.csv"))) {
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("==============================================");
    }




    public static void openCsv2 () throws IOException {
        ArrayList<Box> boxes = new ArrayList<Box>();

        try (BufferedReader reader = new BufferedReader(new FileReader("boxes.csv"))) {
            String str;
            boolean isHeader = true;
            while ((str = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] dataRow = str.split(";");
                int w = Integer.parseInt(dataRow[0]);
                int q = Integer.parseInt(dataRow[1]);
                int p = Integer.parseInt(dataRow[2]);

                Box b = new Box(w, p, q);
                boxes.add(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Box box:boxes) {
            System.out.printf("BOX: weight: %d; quantity: %d; price: %d\r\n", box.weight, box.quantity, box.price);
        }
    }
}
