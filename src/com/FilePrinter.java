package com;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.Main.floatValues;
import static com.Main.intValues;

public class FilePrinter {
    public FilePrinter(String[] splited,String path) throws IOException {
        FileWriter fr = new FileWriter(path,false);
        BufferedWriter br = new BufferedWriter(fr);
        if (intValues.containsKey(splited[1])){
            System.out.println(intValues.get(splited[1]));
            br.write(String.valueOf(intValues.get(splited[1])));
        } else{
            System.out.println(floatValues.get(splited[1]));
            br.write(String.valueOf(floatValues.get(splited[1])));
        }
        br.close();
    }
}
