package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        try {
//            readConfig(file2);
            read_compute(xFile2);
//            read_compute(xFile1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File Not Found!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println(intValues);
//        System.out.println(floatValues);
    }
    public static void read_compute(File file) throws IOException, IllegalArgumentException, ArithmeticException{
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splited = line.split("[\\s]+");
            System.out.println(Arrays.toString(splited));
            if (line.trim().equals("%%"))
                break;
            if (intValues.containsKey(splited[1]) || floatValues.containsKey(splited[1]))
                throw new IllegalArgumentException("Duplicated variable");
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
        }
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] splited = line.split("[\\s]+");
            System.out.println(Arrays.toString(splited));
            compute(scanner, splited);
        }
    }

    public static void compute(Scanner scanner, String[] splited){
        if(splited[0].equals("for")){
            ArrayList<String> forLines = new ArrayList<>();
            int i=0;
            while (true){
                forLines.add(scanner.nextLine());
                if (forLines.get(i).trim().equals("end for")){
                    forLines.remove(i);
                    break;
                }
                i++;
            }
            for (int j = 0; j < Integer.parseInt(splited[1]); j++) {
                for (String str : forLines) {
                    compute(scanner,str.split("[\\s]+"));
                }
            }
        }else if(splited[0].equals("print")){
            if (intValues.containsKey(splited[1]))
                System.out.println(intValues.get(splited[1]));
            else
                System.out.println(floatValues.get(splited[1]));
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
                intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])-intValues.get(splited[4]));
            else if (floatValues.containsKey(splited[0]))
                floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])-floatValues.get(splited[4]));
        } else if (splited[3].equals("*")){
            if (intValues.containsKey(splited[0]))
                intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])*intValues.get(splited[4]));
            else if (floatValues.containsKey(splited[0]))
                floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])*floatValues.get(splited[4]));
        } else if (splited[3].equals("/")){
            if (intValues.containsKey(splited[0]))
                if(intValues.get(splited[4])==0)
                    throw new ArithmeticException();
                else
                    intValues.computeIfPresent(splited[0],(k,v)->intValues.get(splited[2])/intValues.get(splited[4]));
            else if (floatValues.containsKey(splited[0]))
                if(floatValues.get(splited[4])==0)
                    throw new ArithmeticException();
                else
                    floatValues.computeIfPresent(splited[0],(k,v)->floatValues.get(splited[2])/floatValues.get(splited[4]));
        }
    }

}
