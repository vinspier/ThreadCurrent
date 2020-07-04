package customizeThreadPool;

import java.util.LinkedList;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long> {

    private long gap;// 每组的间隔大小
    private long start;// 开始位置
    private long end;// 结束位置

    public CountTask(long start, long end,long gap) {
        this.gap = gap;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0L;
        boolean separate = (end - start) > gap;
        if (!separate){
            sum = count(start,end);
        }else {
            LinkedList<CountTask> subCountTasks = new LinkedList<>();
            for (long i = start; i < end; i += gap + 1){
                long endPos = i + gap;
                if (endPos > end){
                    endPos = end;
                }
                CountTask countTask = new CountTask(i, endPos, gap);
                subCountTasks.add(countTask);
                countTask.fork();
            }
            for (CountTask c: subCountTasks) {
                sum += c.join();
            }
        }
        return sum;
    }

    private long count(long start,long end){
        long sum = 0L;
        while (start <= end){
            sum += start;
            start++;
        }
        return sum;
    }
}
