package Thread.TaskReturnValue.serial;

import Thread.TaskReturnValue.common.BestMatchingData;
import Thread.TaskReturnValue.common.LevenshteinDistance;
import Thread.TaskReturnValue.common.WordsLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 串行方式寻找最佳匹配字符串组
 */
public class SerialMatch {

    public static BestMatchingData getBestMatchingWords(String target){
        List<String> dictionary = new LinkedList<>();
        try {
            dictionary = WordsLoader.load("resources/UKACD.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int minDistance = Integer.MAX_VALUE;
        List<String> matchData = new ArrayList<>();
        for (String source : dictionary){
            int distance = LevenshteinDistance.calculate(source,target);
            if (distance <= minDistance){
                if (distance < minDistance){
                    minDistance = distance;
                    matchData.clear();
                }
                matchData.add(source);
            }
        }
        return new BestMatchingData(matchData,minDistance);
    }

}
