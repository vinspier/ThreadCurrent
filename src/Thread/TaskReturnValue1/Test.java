package Thread.TaskReturnValue1;

import Thread.TaskReturnValue1.parallel.ParallelSingleIndex;
import Thread.TaskReturnValue1.serial.SerialIndex;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Test {
    public static void main(String[] args) {
         testSerialInvertedIndex();
         testParallelSingle();
    }

    public static void testSerialInvertedIndex(){
        SerialIndex serialIndex = new SerialIndex();
        Map<String,List<String>> invertedMap = serialIndex.parse();
        for (Map.Entry<String,List<String>> value : invertedMap.entrySet()){
            System.out.println(value.toString());
        }
    }

    public static void testParallelSingle(){
        ParallelSingleIndex parallelSingleIndex = new ParallelSingleIndex();
        ConcurrentHashMap<String,ConcurrentLinkedDeque<String>> result = parallelSingleIndex.invertedIndex("resources/words");
        for (Map.Entry<String,ConcurrentLinkedDeque<String>> value : result.entrySet()){
            System.out.println(value.toString());
        }
    }
}
