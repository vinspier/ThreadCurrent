package Thread.TaskReturnValue1.parallel;

import Thread.TaskReturnValue1.common.DocumentData;
import Thread.TaskReturnValue1.task.DocumentMultipleParseTask;
import Thread.TaskReturnValue1.task.DocumentParseTask;
import Thread.TaskReturnValue1.task.WordsInvertedIndexTask;
import Thread.TaskReturnValue1.task.WordsMultipleInvertedIndexTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: ParallelSingleIndex
 * @Description: 多个文档分配一个任务去执行
 * @Author:
 * @Date: 2019/11/13 11:28
 * @Version V1.0
 **/
public class ParallelMultipleIndex {

    private long start,end;

    private ThreadPoolExecutor executor;

    private CompletionService<List<DocumentData>> completionService;

    private ConcurrentHashMap<String,ConcurrentLinkedDeque<String>> result;

    private int taskSize;

    public ParallelMultipleIndex(int taskSize) {
        executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(Math.max((Runtime.getRuntime().availableProcessors() - 1),1));
        completionService = new ExecutorCompletionService<>(executor);
        result = new ConcurrentHashMap<>();
        this.taskSize = taskSize;
    }

    public ConcurrentHashMap<String,ConcurrentLinkedDeque<String>> invertedIndex(String path){
        File file = new File(path);
        File[] doucuments = file.listFiles();
        start = System.currentTimeMillis();
        List<File> taskFiles = new ArrayList<>();
        int index = 0;
        /**
         * 创建转换文档任务
         */
        for (File f : doucuments){
            taskFiles.add(f);
            if(index == doucuments.length - 1){
                DocumentMultipleParseTask task = new DocumentMultipleParseTask(taskFiles);
                completionService.submit(task);
            }else {
                if (taskFiles.size() == taskSize){
                    DocumentMultipleParseTask task = new DocumentMultipleParseTask(taskFiles);
                    completionService.submit(task);
                    taskFiles = new ArrayList<>();
                }
            }
            index ++;
            while (executor.getQueue().size() > 10){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        WordsMultipleInvertedIndexTask invertedIndexTask0 = new WordsMultipleInvertedIndexTask(completionService,result);
        WordsMultipleInvertedIndexTask invertedIndexTask1 = new WordsMultipleInvertedIndexTask(completionService,result);
        Thread thread0 = new Thread(invertedIndexTask0);
        Thread thread1 = new Thread(invertedIndexTask1);
        thread0.start();
        thread1.start();
        executor.shutdown();
        /**
         * 等待所有的WordsMultipleInvertedIndexTask任务执行完毕
         * */
        try {
            executor.awaitTermination(12,TimeUnit.HOURS);
            thread0.interrupt();
            thread1.interrupt();
            thread0.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("=================== time took ===================");
        System.out.println(end - start + " ms");
        System.out.println("=================== time took ===================");
        return result;
    }

    public int getTaskSize() {
        return taskSize;
    }

    public void setTaskSize(int taskSize) {
        this.taskSize = taskSize;
    }
}
