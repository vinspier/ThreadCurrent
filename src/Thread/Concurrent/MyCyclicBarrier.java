package Thread.Concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// 就像集体旅游一样 等人齐了再出发去下一个地点  .await()  .getNumberWaiting();
public class MyCyclicBarrier {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for(int i=0;i<5;i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long)Math.random()*1000);
                        System.out.println(Thread.currentThread().getName()+"即将到达集合点1   "+ "当前已有线程数："
                                +(cyclicBarrier.getNumberWaiting()+1)+(cyclicBarrier.getNumberWaiting()==4?"     5个已到齐 继续向前":"     还没到齐 继续等待"));
                        cyclicBarrier.await();

                        Thread.sleep((long)Math.random()*1000);
                        System.out.println(Thread.currentThread().getName()+"即将到达集合点2   "+ "当前已有线程数："
                                +(cyclicBarrier.getNumberWaiting()+1)+(cyclicBarrier.getNumberWaiting()==4?"     5个已到齐 继续向前":"     还没到齐 继续等待"));
                        cyclicBarrier.await();

                        Thread.sleep((long)Math.random()*1000);
                        System.out.println(Thread.currentThread().getName()+"即将到达集合点3   "+ "当前已有线程数："
                                +(cyclicBarrier.getNumberWaiting()+1)+(cyclicBarrier.getNumberWaiting()==4?"     5个已到齐 继续向前":"     还没到齐 继续等待"));
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

}
