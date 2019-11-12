package Thread.TaskReturnValue1;

import Thread.TaskReturnValue1.serial.SerialIndex;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        testSerialInvertedIndex();
    }

    public static void testSerialInvertedIndex(){
        SerialIndex serialIndex = new SerialIndex();
        Map<String,List<String>> invertedMap = serialIndex.parse();
        System.out.println(invertedMap.toString());
    }
}
