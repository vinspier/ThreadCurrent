package Thread.executors.RSS;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @ClassName: ExecutorTask
 * @Description:
 * @Author:
 * @Date: 2019/11/8 14:24
 * @Version V1.0
 **/
public class ExecutorTask<V> extends FutureTask<V>  implements RunnableScheduledFuture<V>{

    private RunnableScheduledFuture<V> task;

    private NewsExecutor executor;

    private long startDate;

    public ExecutorTask(Callable<V> callable) {
        super(callable);
    }

    public ExecutorTask(Runnable runnable, V result, RunnableScheduledFuture<V> task, NewsExecutor executor) {
        super(runnable, result);
        this.task = task;
        this.executor = executor;
        this.startDate = new Date().getTime();
    }

    @Override
    public void run() {
        boolean isPeriodic = isPeriodic();
        if (isPeriodic && !executor.isShutdown()){
            super.run();
            Date now = new Date();
            startDate = now.getTime() + Timer.getPeriod();
            executor.getQueue().add(this);
            System.out.println("Start Date: " + new Date(startDate));
        }
    }

    @Override
    public boolean isPeriodic() {
        return Timer.getPeriod() != 0;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Long delay;
        if (!isPeriodic()){
            delay = task.getDelay(unit);
        }else {
            if (startDate == 0){
                delay = task.getDelay(unit);
            }else {
                Date current = new Date();
                delay = startDate - current.getTime();
                delay = unit.convert(delay,TimeUnit.MILLISECONDS);
            }
        }
        return delay;
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.getStartDate(),((ExecutorTask<V>) o).getStartDate());
    }

    public RunnableScheduledFuture<V> getTask() {
        return task;
    }

    public void setTask(RunnableScheduledFuture<V> task) {
        this.task = task;
    }

    public NewsExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(NewsExecutor executor) {
        this.executor = executor;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
}
