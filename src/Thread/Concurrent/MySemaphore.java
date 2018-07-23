package Thread.Concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MySemaphore {
    private static ExecutorService executorService =  Executors.newCachedThreadPool();
    //单个信号的时候 可以实现互斥效果 .availablePermits()  .acquire()  .release()
    private static Semaphore semaphore = new Semaphore(4);
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "进入，当前已有" + (4-semaphore.availablePermits()) + "个并发");
                    try {
                        Thread.sleep((long)(Math.random()*10000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "即将离开");
                    semaphore.release();
                    //下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "已离开，当前已有" + (4-semaphore.availablePermits()) + "个并发");
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
}
