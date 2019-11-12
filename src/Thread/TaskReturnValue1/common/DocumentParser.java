package Thread.TaskReturnValue1.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析文档文件为文档存储对象
 */
public class DocumentParser {

    public static Map<String,Integer> parse(String path){
        Map<String,Integer> words = new HashMap<>();
        Path file = Paths.get(path);
        try {
            List<String> lines = Files.readAllLines(file);
            for (String line : lines){
                parseLine(line,words);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static void parseLine(String line, Map<String,Integer> words){
        words.merge(line.toLowerCase(),1,(a,b) -> a + b);
    }

}
