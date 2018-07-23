package Thread.Concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//阻塞队列 当队列数据已经填充满时 存放数据操作就会等待取数据操作完成使队列不为满 然后存放操作继续执行
public class MyBlockingQueue {
    final static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(4);

    public static void main(String[] args) {

        for(int i=0;i<4;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long)(Math.random()*1000));
                            System.out.println(Thread.currentThread().getName()+"准备存放数据---3");
                            blockingQueue.put(3);
                            System.out.println(Thread.currentThread().getName()+"已经存放数据--3" + "     现在共有"+blockingQueue.size()+"个数据");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep((long)(Math.random()*1000));
                        System.out.println(Thread.currentThread().getName()+"准备取走数据---3");
                        blockingQueue.take();
                        System.out.println(Thread.currentThread().getName()+"已经取走数据--3" + "     现在共有"+blockingQueue.size()+"个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
