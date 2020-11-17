package Thread.test;

import java.util.HashMap;

public class TestHashMap {
    public static void main(String[] args) {
        HashMap<String,Object> map = new HashMap<>();
        String key;
        for (Integer i = 0; i < 13; i++){
            key = "" + i;
            map.put(key,i);
        }
    }



}
