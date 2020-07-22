package Thread.Concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 使用阻塞队列完成线程之间的同步关系
 * */
public class BlockingQueueCommunication {
    final static Business business = new Business();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
            for (int i = 0; i < 20; i++) {
                int index = i;
                executorService.submit(() -> {
                    business.method1(index);
                });
            }
            for(int i=0;i<20;i++){
                int index = i;
                executorService.submit(() -> {
                    business.method2(index);
                });
            }
            executorService.shutdown();
    }
}

class Business{
    private BlockingQueue<Integer> blockingQueue1 = new ArrayBlockingQueue<Integer>(1);
    private BlockingQueue<Integer> blockingQueue2 = new ArrayBlockingQueue<Integer>(1);
    //匿名方法
    {
        try {
            blockingQueue2.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method1(int i){
        try {
            blockingQueue1.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method1 task --- 子线程 " + "第" + (i + 1) + "個任務");
        try {
            blockingQueue2.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2(int i){
        try {
            blockingQueue2.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2 task --- 主线程 "+  "第" + ( i + 1 ) + "個任務");
        try {
            blockingQueue1.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}