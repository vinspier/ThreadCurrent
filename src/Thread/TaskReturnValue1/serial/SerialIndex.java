package Thread.TaskReturnValue1.serial;

import Thread.TaskReturnValue1.common.DocumentParser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  为所有文档建立 词汇倒排索引
 */
public class SerialIndex {
    Long start, end;
    File source = new File("resources/words");
    File[] files = source.listFiles();
    Map<String, List<String>> invertedIndex = new HashMap<String,List<String>>();

    public Map<String, List<String>> parse(){
        start = System.currentTimeMillis();
        File[] files = source.listFiles();
        for (File file : files){
            if (file.getName().endsWith("txt")){
                Map<String,Integer> words = DocumentParser.parse(file.getAbsolutePath());
                refreshInvertedIndex(words,invertedIndex,file.getName());
            }
        }
        end = System.currentTimeMillis();
        System.out.println("time took : " + (end - start) + " ms");
        return invertedIndex;
    }


    private void refreshInvertedIndex(Map<String,Integer> words,Map<String, List<String>> invertedIndex,String filename){
        for (String word : words.keySet()){
            invertedIndex.computeIfAbsent(word,k -> new ArrayList<>()).add(filename);
        }
    }

}
