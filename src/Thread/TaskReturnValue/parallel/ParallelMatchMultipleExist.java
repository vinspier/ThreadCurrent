package Thread.TaskReturnValue.parallel;

import Thread.TaskReturnValue.common.WordsLoader;
import Thread.TaskReturnValue.task.ExistBasicTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ParallelMatchMultipleExist
 * @Description: 使用 AbstractExecutorService 定义的 invokeAny方法
 * 返回第一个执行且无误的任务的处理结果
 * @Author:
 * @Date: 2019/11/11 10:54
 * @Version V1.0
 **/
public class ParallelMatchMultipleExist {

    public static Map<String,Object> find(String target) throws InterruptedException,ExecutionException{
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
        List<ExistBasicTask> tasks = new ArrayList<>();
        for (int i = 0; i < poolSize; i++){
            ExistBasicTask task = new ExistBasicTask(start,end,target,dictionary);
            tasks.add(task);
        }
        return executor.invokeAny(tasks);
    }

}
