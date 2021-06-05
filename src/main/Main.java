package main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String,Integer> intValues = new HashMap<>();
    static HashMap<String,Float> floatValues = new HashMap<>();

    public static void main(String[] args) {
        File file = new File("src\\input1.txt");
        try {
            readConfig(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(intValues);
        System.out.println(floatValues);
    }
    public static void readConfig(File file) throws FileNotFoundException , IllegalArgumentException{
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splited = line.split(" ");
            System.out.println(Arrays.toString(splited));
//            if (intValues.containsKey(splited[1]) || floatValues.containsKey(splited[1]))
//                throw new IllegalArgumentException("Duplicated variable");
            if (splited[0].equals("int")) {
                if (splited.length==4)
                    intValues.put(splited[1], Integer.parseInt(splited[3]));
                else if (splited.length==2)
                    intValues.put(splited[1],0);
            } else if (splited[0].equals("float")) {
                if (splited.length==4)
                    floatValues.put(splited[1], Float.parseFloat(splited[3]));
                else if (splited.length==2)
                    floatValues.put(splited[1],0.0f);
            }
            if (line.trim().equals("%%"))
                break;
        }
    }
}
