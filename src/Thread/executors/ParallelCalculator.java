package Thread.executors;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: ParrallelCalculator
 * @Description: 用于并发计算的task
 * @Author:
 * @Date: 2019/11/4 14:51
 * @Version V1.0
 **/
public class ParallelCalculator implements Runnable{

    /**
     * 原始训练数据集合
     * */
    private final List<? extends Sample> dataSet;

    /**
     * 起始，终止 下标
     */
    private final int start,end;

    /**
     * 计算结果的存放集合
     */
    private final Distance[] distances;

    /**
     * 比对目标
     */
    private final Sample sample;

    /**
     * 线程并发执行的调度器之一(等待类型)
     */
    private final CountDownLatch countDownLatch;

    public ParallelCalculator(List<? extends Sample> dataSet, int start, int end, Distance[] distances, Sample sample, CountDownLatch countDownLatch) {
        this.dataSet = dataSet;
        this.start = start;
        this.end = end;
        this.distances = distances;
        this.sample = sample;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++){
            Distance distance = new Distance();
            distance.setIndex(i);
            distance.setDistance(DistanceCalculator.calculate(dataSet.get(i),sample));
            distances[i] = distance;
        }
        countDownLatch.countDown();
        System.out.println("===========" + end);
    }
}
