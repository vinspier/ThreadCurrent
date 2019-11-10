package Thread.TaskReturnValue.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 加载UKACD.TXT的字符串内容
 */
public class WordsLoader {

    public static List<String> load(String path) throws IOException{
        List<String> dictionary = new ArrayList<>();
        FileReader reader = new FileReader(new File(path));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String result;
        while ((result = bufferedReader.readLine()) != null){
            dictionary.add(result);
           // System.out.println(result);
        }
        return dictionary;
    }

}
