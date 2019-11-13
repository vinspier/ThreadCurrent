package Thread.TaskReturnValue1.parallel;

import Thread.TaskReturnValue1.common.DocumentData;
import Thread.TaskReturnValue1.task.DocumentParseTask;
import Thread.TaskReturnValue1.task.WordsInvertedIndexTask;

import java.io.File;
import java.util.concurrent.*;

/**
 * @ClassName: ParallelSingleIndex
 * @Description: 每个文档分配一个任务去执行
 * @Author:
 * @Date: 2019/11/13 11:28
 * @Version V1.0
 **/
public class ParallelSingleIndex {

    private long start,end;

    private ThreadPoolExecutor executor;

    private CompletionService<DocumentData> completionService;

    private ConcurrentHashMap<String,ConcurrentLinkedDeque<String>> result;

    public ParallelSingleIndex() {
        executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(Math.max((Runtime.getRuntime().availableProcessors() - 1),1));
        completionService = new ExecutorCompletionService<DocumentData>(executor);
        result = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String,ConcurrentLinkedDeque<String>> invertedIndex(String path){
        File file = new File(path);
        File[] doucuments = file.listFiles();
        start = System.currentTimeMillis();
        /**
         * 创建转换文档任务
         */
        for (File f : doucuments){
            DocumentParseTask task = new DocumentParseTask(f);
            completionService.submit(task);
            while (executor.getQueue().size() > 500){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        WordsInvertedIndexTask invertedIndexTask0 = new WordsInvertedIndexTask(completionService,result);
        WordsInvertedIndexTask invertedIndexTask1 = new WordsInvertedIndexTask(completionService,result);
        Thread thread0 = new Thread(invertedIndexTask0);
        Thread thread1 = new Thread(invertedIndexTask1);
        thread0.start();
        thread1.start();
        executor.shutdown();
        /**
         * 等待所有的WordsInvertedIndexTask任务执行完毕
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

}
