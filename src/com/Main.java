package com;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String,Integer> intValues = new HashMap<>();
    static HashMap<String,Float> floatValues = new HashMap<>();

    public static void main(String[] args) {
        File file1 = new File("src\\input1.txt");
        File file2 = new File("src\\input2.txt");
        File xFile1 = new File("src\\input1.x");
        File xFile2 = new File("src\\input2.x");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window window = new Window();
                window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                window.setVisible(true);
                window.toFront();
                window.setBackground(Color.PINK);
            }
        });
    }
    public static void read_compute(File file) throws IOException, IllegalArgumentException, ArithmeticException{
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splited = line.trim().split("[\\s]+");
//            System.out.println(Arrays.toString(splited));
            if (line.trim().equals("%%"))
                break;
            boolean isEmpty = line.isBlank();
            if (!isEmpty && intValues.containsKey(splited[1]) || floatValues.containsKey(splited[1]))
                throw new IllegalArgumentException("Duplicated variable");
            if (!isEmpty && splited[0].equals("int")) {
                if (splited.length == 4){
                    if (intValues.containsKey(splited[3]))
                        intValues.put(splited[1], intValues.get(splited[3]));
                    else if (!intValues.containsKey(splited[3]))
                        intValues.put(splited[1], Integer.parseInt(splited[3]));
                }else if (splited.length==2)
                    intValues.put(splited[1],0);
            } else if (!isEmpty && splited[0].equals("float")) {
                if (splited.length == 4){
                    if (floatValues.containsKey(splited[3]))
                        floatValues.put(splited[1], floatValues.get(splited[3]));
                    else if (!floatValues.containsKey(splited[3]))
                        floatValues.put(splited[1], Float.parseFloat(splited[3]));
                }else if (splited.length==2)
                    floatValues.put(splited[1],0.0f);
            }
        }
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] splited = line.trim().split("[\\s]+");
//            System.out.println(Arrays.toString(splited));
            compute(scanner, splited);
        }
    }

    public static void compute(Scanner scanner, String[] splited) throws IOException {
        if(splited[0].equals("for")){
            ArrayList<String> forLines = new ArrayList<>();
            int i=0;
            while (true){
                forLines.add(scanner.nextLine().trim());
                if (forLines.get(i).trim().equals("end for")){
                    forLines.remove(i);
                    break;
                }
                i++;
            }
            for (int j = 0; j < Integer.parseInt(splited[1]); j++) {
                for (String str : forLines) {
                    compute(scanner,str.trim().split("[\\s]+"));
                }
            }
        }else if(splited[0].equals("print")){
            FileWriter fr = new FileWriter("output.txt",false);
            BufferedWriter br = new BufferedWriter(fr);
            if (intValues.containsKey(splited[1])){
                System.out.println(intValues.get(splited[1]));
                br.write(String.valueOf(intValues.get(splited[1])));
            } else{
                System.out.println(floatValues.get(splited[1]));
                br.write(String.valueOf(floatValues.get(splited[1])));
            }
            br.close();
        } else if (splited[3].equals("+")){
            if (intValues.containsKey(splited[0]))
                if(intValues.containsKey(splited[2]) && intValues.containsKey(splited[4]))
                    intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])+intValues.get(splited[4]));
                else if(intValues.containsKey(splited[2]) && !intValues.containsKey(splited[4]))
                    intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])+Integer.parseInt(splited[4]));
                else if(!intValues.containsKey(splited[2]) && intValues.containsKey(splited[4]))
                    intValues.computeIfPresent(splited[0],(k,v)-> Integer.parseInt(splited[2])+ intValues.get(splited[4]));
            else if (floatValues.containsKey(splited[0]))
                    if(floatValues.containsKey(splited[2]) && floatValues.containsKey(splited[4]))
                        floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])+floatValues.get(splited[4]));
                    else if(floatValues.containsKey(splited[2]) && !floatValues.containsKey(splited[4]))
                        floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])+Float.parseFloat(splited[4]));
                    else if(!floatValues.containsKey(splited[2]) && floatValues.containsKey(splited[4]))
                        floatValues.computeIfPresent(splited[0],(k,v)-> Float.parseFloat(splited[2])+ floatValues.get(splited[4]));
        } else if (splited[3].equals("-")){
            if (intValues.containsKey(splited[0]))
                if(intValues.containsKey(splited[2]) && intValues.containsKey(splited[4]))
                    intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])-intValues.get(splited[4]));
                else if(intValues.containsKey(splited[2]) && !intValues.containsKey(splited[4]))
                    intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])-Integer.parseInt(splited[4]));
                else if(!intValues.containsKey(splited[2]) && intValues.containsKey(splited[4]))
                    intValues.computeIfPresent(splited[0],(k,v)-> Integer.parseInt(splited[2])-intValues.get(splited[4]));
            else if (floatValues.containsKey(splited[0]))
                    if(floatValues.containsKey(splited[2]) && floatValues.containsKey(splited[4]))
                        floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])-floatValues.get(splited[4]));
                    else if(floatValues.containsKey(splited[2]) && !floatValues.containsKey(splited[4]))
                        floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])-Float.parseFloat(splited[4]));
                    else if(!floatValues.containsKey(splited[2]) && floatValues.containsKey(splited[4]))
                        floatValues.computeIfPresent(splited[0],(k,v)-> Float.parseFloat(splited[2])-floatValues.get(splited[4]));
        } else if (splited[3].equals("*")){
            if (intValues.containsKey(splited[0]))
                if(intValues.containsKey(splited[2]) && intValues.containsKey(splited[4]))
                    intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])*intValues.get(splited[4]));
                else if(intValues.containsKey(splited[2]) && !intValues.containsKey(splited[4]))
                    intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])*Integer.parseInt(splited[4]));
                else if(!intValues.containsKey(splited[2]) && intValues.containsKey(splited[4]))
                    intValues.computeIfPresent(splited[0],(k,v)-> Integer.parseInt(splited[2])*intValues.get(splited[4]));
            else if (floatValues.containsKey(splited[0]))
                    if(floatValues.containsKey(splited[2]) && floatValues.containsKey(splited[4]))
                        floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])*floatValues.get(splited[4]));
                    else if(floatValues.containsKey(splited[2]) && !floatValues.containsKey(splited[4]))
                        floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])*Float.parseFloat(splited[4]));
                    else if(!floatValues.containsKey(splited[2]) && floatValues.containsKey(splited[4]))
                        floatValues.computeIfPresent(splited[0],(k,v)-> Float.parseFloat(splited[2])*floatValues.get(splited[4]));
        } else if (splited[3].equals("/")){
            if (intValues.containsKey(splited[0]))
                if(intValues.containsKey(splited[2]) && intValues.containsKey(splited[4]) && intValues.get(splited[4])!=0)
                    intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])/intValues.get(splited[4]));
                else if(intValues.containsKey(splited[2]) && !intValues.containsKey(splited[4]) && Integer.parseInt(splited[4])!=0)
                    intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])/Integer.parseInt(splited[4]));
                else if(!intValues.containsKey(splited[2]) && intValues.containsKey(splited[4]) && intValues.get(splited[4])!=0)
                    intValues.computeIfPresent(splited[0],(k,v)-> Integer.parseInt(splited[2])/intValues.get(splited[4]));
                else
                    throw new ArithmeticException();
            else if (floatValues.containsKey(splited[0]))
                if (floatValues.containsKey(splited[2]) && floatValues.containsKey(splited[4]) && floatValues.get(splited[4])!=0)
                    floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])/floatValues.get(splited[4]));
                else if (floatValues.containsKey(splited[2]) && !intValues.containsKey(splited[4]) && Float.parseFloat(splited[4])!=0)
                    floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])/Float.parseFloat(splited[4]));
                else if (!floatValues.containsKey(splited[2]) && floatValues.containsKey(splited[4]) && floatValues.get(splited[4])!=0)
                    floatValues.computeIfPresent(splited[0],(k,v)->Float.parseFloat(splited[2])/floatValues.get(splited[4]));
        }
    }
}
