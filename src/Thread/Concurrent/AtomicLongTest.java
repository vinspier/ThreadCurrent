package Thread.Concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: AtomicLongTest
 * @Description:
 * @Author:
 * @Date: 2019/11/8 8:47
 * @Version V1.0
 **/
public class AtomicLongTest {

    private static  AtomicLong count = new AtomicLong(0L);
    private static Long total = 0L;
    private static Long total1 = 0L;

    private static ExecutorService executorService;

    private static Semaphore semaphore = new Semaphore(1000);
    private static CountDownLatch downLatch = new CountDownLatch(1000);

    public static void main(String[] args) {
        count(1000);
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("=============================>" + count);
        System.out.println("total1:=============================>" + total1);
        testTotal(1000);
    }

    public static void count(int times){
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < times; i++){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    count.incrementAndGet();
                    total1++;
                    downLatch.countDown();

                }
            };
            executorService.execute(task);
        }
    }

    public static void testTotal(int times){
        for (int i = 0; i < times; i++){
            total++;
        }
        System.out.println("total:=============================>" + total);
    }
}
