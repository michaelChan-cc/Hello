package jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OutOfMemoryGCLimitExceed {
    public static void addRandomDataToMap() {
        Map<Integer, String> dataMap = new HashMap<>();
        Random r = new Random();
        while (true) {
            String val = String.valueOf(r.nextInt());
            dataMap.put(r.nextInt(), val);
            System.out.println(val);
        }
    }

    public static void main(String[] args) {
        addRandomDataToMap();
    }
}
