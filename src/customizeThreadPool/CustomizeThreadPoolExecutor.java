package customizeThreadPool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 根据经验 线程的池的大小计算方式
 *
 * Nthreads = Ncpu * Ucpu * ( 1 + W/C)
 * Nthreads 为池的线程大小
 * Ncpu 计算机能提供的最大核数量
 * W 任务的等待时间
 * C 任务的执行计算时间
 *
 *
 * */
public class CustomizeThreadPoolExecutor extends ThreadPoolExecutor {

    public CustomizeThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                       BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public CustomizeThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                       BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    /**
     * 提供处理任务的前置方法 默认为空逻辑
     * */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println(t.getName() + " prepare running at " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    /**
     * 提供处理任务的后置方法 默认为空逻辑
     * 可处理 抛出的异常
     * */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println( Thread.currentThread().getName() + " has been executed at " +  LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        if (t != null){
            System.out.println( Thread.currentThread().getName() + " has throws an exception at " +
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    + " " + t.getMessage());
            t.printStackTrace();
        }
    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command));
    }

    /**
     * 将任务包装 尝试获取到真实任务的运行异常
     * */
    private Runnable wrap(Runnable r){
        return ()-> {
            try {
                r.run();
            } catch (Exception e) {
                throw e;
            }
        };
    }

    /**
     * 线程池关闭的动作
     * */
    @Override
    protected void terminated() {
        System.out.println("thread pool terminated at " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
