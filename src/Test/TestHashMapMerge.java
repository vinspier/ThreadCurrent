package Test;

import java.util.HashMap;
import java.util.Map;

public class TestHashMapMerge {

    public static void main(String[] args) {
        testMerge();
    }

    public static void testMerge(){
        Map<String,Integer> dataMap = new HashMap<>();
        String tag = "123";
        for (int i = 0 ; i < 5 ; i++){
            dataMap.merge(tag,1,(a,b) -> a + b);
        }
        System.out.println(dataMap.keySet());
    }
}
