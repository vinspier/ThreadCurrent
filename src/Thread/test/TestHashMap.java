package Thread.test;

import java.util.HashMap;

public class TestHashMap {
    public static void main(String[] args) {
        HashMap<String,Object> map = new HashMap<>();
        for (int i = 0; i < 13; i++){
            map.put("" + i,i);
        }
    }



}
