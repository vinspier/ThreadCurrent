package Thread.executors.RSS;

import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @ClassName: NewsExecutor
 * @Description:
 * @Author:
 * @Date: 2019/11/8 14:54
 * @Version V1.0
 **/
public class NewsExecutor extends ScheduledThreadPoolExecutor {

    public NewsExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> task) {
        ExecutorTask<V> myTask = new ExecutorTask<V>(runnable,null,task,this);
        return myTask;
    }
}
