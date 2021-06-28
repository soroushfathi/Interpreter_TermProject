package com;

public class Sum {
    public Sum(String[] splited) {
        if (Main.intValues.containsKey(splited[0]))
            if(Main.intValues.containsKey(splited[2]) && Main.intValues.containsKey(splited[4]))
                Main.intValues.computeIfPresent(splited[0],(k,v)->Main.intValues.get(splited[2])+Main.intValues.get(splited[4]));
            else if(Main.intValues.containsKey(splited[2]) && !Main.intValues.containsKey(splited[4]))
                Main.intValues.computeIfPresent(splited[0],(k,v)->Main.intValues.get(splited[2])+Integer.parseInt(splited[4]));
            else if(!Main.intValues.containsKey(splited[2]) && Main.intValues.containsKey(splited[4]))
                Main.intValues.computeIfPresent(splited[0],(k,v)-> Integer.parseInt(splited[2])+ Main.intValues.get(splited[4]));
            else if (Main.floatValues.containsKey(splited[0]))
                if(Main.floatValues.containsKey(splited[2]) && Main.floatValues.containsKey(splited[4]))
                    Main.floatValues.computeIfPresent(splited[0],(k,v)->Main.floatValues.get(splited[2])+Main.floatValues.get(splited[4]));
                else if(Main.floatValues.containsKey(splited[2]) && !Main.floatValues.containsKey(splited[4]))
                    Main.floatValues.computeIfPresent(splited[0],(k,v)->Main.floatValues.get(splited[2])+Float.parseFloat(splited[4]));
                else if(!Main.floatValues.containsKey(splited[2]) && Main.floatValues.containsKey(splited[4]))
                    Main.floatValues.computeIfPresent(splited[0],(k,v)-> Float.parseFloat(splited[2])+ Main.floatValues.get(splited[4]));
    }
}
