package Thread.TaskReturnValue.task;

import Thread.TaskReturnValue.common.BestMatchingData;
import Thread.TaskReturnValue.common.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 对匹配字典 进行 区间计算匹配
 */
public class BestMatchingBasicTask implements Callable<BestMatchingData> {

    private int start;

    private int end;

    private String target;

    private List<String> dictionary;

    public BestMatchingBasicTask(int start, int end, String target, List<String> dictionary) {
        this.start = start;
        this.end = end;
        this.target = target;
        this.dictionary = dictionary;
    }

    @Override
    public BestMatchingData call() throws Exception {
        int minDistance = Integer.MAX_VALUE;
        List<String> matchData = new ArrayList<>();
        for (int i = start; i < end; i++){
            int distance = LevenshteinDistance.calculate(dictionary.get(i),target);
            if (distance <= minDistance){
                if (distance < minDistance){
                    minDistance = distance;
                    matchData.clear();
                }
                matchData.add(dictionary.get(i));
            }
        }
        return new BestMatchingData(matchData,minDistance);
    }
}
