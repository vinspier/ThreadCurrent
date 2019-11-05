package Thread.executors;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: ParallelK
 * @Description: 并发方式k邻近计算
 * @Author:
 * @Date: 2019/11/4 14:09
 * @Version V1.0
 **/
public class ParallelK {

    private final List<? extends Sample> dataSet;

    private final int k;

    /**
     * 以计算机运行时能提供的最大线程数为基数
     * 创建线程池的线程大小 basic * available
     * */
    private final int basic;

    /**
     * 线程池中 最大工作线程数
     * */
    private final int executeTop;

    private final ExecutorService executor;

    /**
     * 线程并发执行的调度器之一(等待类型)
     */
    private final CountDownLatch countDownLatch;

    public ParallelK(List<? extends Sample> dataSet, int k) {
        this.dataSet = dataSet;
        this.k = k;
        this.basic = 1;
        this.executeTop = basic * Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(executeTop);
        this.countDownLatch = new CountDownLatch(executeTop);
    }

    public String classify(Sample target) throws InterruptedException{
       // List<Distance> distanceList = new ArrayList<>(dataSet.size());
        Distance[] distances = new Distance[dataSet.size()];
        int increment = dataSet.size() / (executeTop - 1);
        /**
         * 遍历计算目标点与续联集合点的距离(串行计算)
         */
        int startIndex = 0;
        int endIndex = increment;
        for (int i = 0; i < executeTop; i++){
            ParallelCalculator task = new ParallelCalculator(dataSet,startIndex,endIndex,distances,target,countDownLatch);
            startIndex = endIndex;
            if (i < executeTop - 2){
                endIndex += increment;
            }else {
                /** 最后一个线程执行剩余未分配的子任务 最后一个线程留给主线程 */
                endIndex = dataSet.size();
            }
            executor.execute(task);
        }
        countDownLatch.await();
        executor.shutdown();
        return DistanceCalculator.getK(Arrays.asList(distances),dataSet,k);
    }

    public void destroy() {
        executor.shutdown();
    }
}
