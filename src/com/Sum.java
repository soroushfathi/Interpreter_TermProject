package com;

import static com.Main.floatValues;
import static com.Main.intValues;

public class Sum {
    public Sum(String[] splited) {
        if (intValues.containsKey(splited[0]))
            if(intValues.containsKey(splited[2]) && intValues.containsKey(splited[4]))
                intValues.computeIfPresent(splited[0],(k,v)-> intValues.get(splited[2])+ intValues.get(splited[4]));
            else if(intValues.containsKey(splited[2]) && !intValues.containsKey(splited[4]))
                intValues.computeIfPresent(splited[0],(k,v)-> intValues.get(splited[2])+Integer.parseInt(splited[4]));
            else if(!intValues.containsKey(splited[2]) && intValues.containsKey(splited[4]))
                intValues.computeIfPresent(splited[0],(k,v)-> Integer.parseInt(splited[2])+ intValues.get(splited[4]));
            else if (floatValues.containsKey(splited[0]))
                if(floatValues.containsKey(splited[2]) && floatValues.containsKey(splited[4]))
                    floatValues.computeIfPresent(splited[0],(k,v)-> floatValues.get(splited[2])+ floatValues.get(splited[4]));
                else if(floatValues.containsKey(splited[2]) && !floatValues.containsKey(splited[4]))
                    floatValues.computeIfPresent(splited[0],(k,v)-> floatValues.get(splited[2])+Float.parseFloat(splited[4]));
                else if(!floatValues.containsKey(splited[2]) && floatValues.containsKey(splited[4]))
                    floatValues.computeIfPresent(splited[0],(k,v)-> Float.parseFloat(splited[2])+ floatValues.get(splited[4]));
    }
}
