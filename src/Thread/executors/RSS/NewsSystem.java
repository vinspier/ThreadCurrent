package Thread.executors.RSS;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NewsSystem implements Runnable {

    /**
     * 生产新闻的最大次数
     */
    private final int times;

    private final ScheduledThreadPoolExecutor executor;

    private final NewsBuffer buffer;

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    private boolean interrupt;

    public NewsSystem(int times) {
        this.interrupt = false;
        this.times = times;
        this.executor = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
        this.buffer = new NewsBuffer();
    }

    @Override
    public void run() {
        Random random = new Random(times);
        /**
         * 开启消费线程
         */
        Thread newsConsumer =  new Thread(new NewsConsumer(buffer));
        executor.submit(newsConsumer);
        while (!interrupt){
            NewsProduce newsProduce = new NewsProduce(random.nextInt(),buffer);
            try {
                Thread.sleep(1000);
                executor.scheduleWithFixedDelay(newsProduce,0,1, TimeUnit.MINUTES);
            } catch (Exception e) {

            }
        }
        /**
         * 同步自己 并且等待
         * 直到外界调用shutdown方法 关闭生产
         */
        synchronized (this){
            try {
                System.out.println("<<<<<< =========================== enter the synchronized method ===========================>>>>>>");
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("<<<<<< =========================== stop the thread of newsConsumer ===========================>>>>>>");
        executor.shutdown();
        System.out.println("<<<<<< =========================== shutdown the thread of executor ===========================>>>>>>");
        System.out.println("所有任务结束");
    }

    public void shutdown(){
        interrupt = true;
        countDownLatch.countDown();
    }

}
