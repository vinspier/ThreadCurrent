package Thread.TaskReturnValue.parallel;

import Thread.TaskReturnValue.common.BestMatchingData;
import Thread.TaskReturnValue.common.WordsLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ParallelMatchMultiple {

    public static BestMatchingData getMatchData(String target) throws InterruptedException,ExecutionException {
        List<Future<BestMatchingData>> futures = new ArrayList<>();
        int poolSize = Runtime.getRuntime().availableProcessors();
        List<String> dictionary = new ArrayList<>();
        try {
            dictionary = WordsLoader.load("resources/UKACD.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
        int increment = dictionary.size() / poolSize;
        int start = 0;
        int end = increment;
        List<BestMatchingBasicTask> tasks = new ArrayList<>();
        for (int i = 0; i < poolSize; i++){
            BestMatchingBasicTask task = new BestMatchingBasicTask(start,end,target,dictionary);
            tasks.add(task);
            start = end;
            if (i == poolSize - 1){
                end = dictionary.size();
            }else {
                end += increment;
            }
        }
        futures = executor.invokeAll(tasks);
        executor.shutdown();
        return analyzeFutures(futures);
    }

    private static BestMatchingData analyzeFutures(List<Future<BestMatchingData>> futures ) throws InterruptedException,ExecutionException{
        int minDistance = Integer.MAX_VALUE;
        List<String> matchData = new ArrayList<>();
        for (Future<BestMatchingData> future : futures){
            BestMatchingData matchingData = future.get();
            int distance = matchingData.getDistance();
            if (distance <= minDistance){
                if (distance < minDistance){
                    minDistance = distance;
                    matchData.clear();
                }
                matchData.addAll(matchingData.getMatchData());
            }
        }
        return new BestMatchingData(matchData,minDistance);
    }


}
