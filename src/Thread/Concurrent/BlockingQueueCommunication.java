package Thread.Concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueCommunication {
    final static Business business = new Business();

    public static void main(String[] args) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 20; i++) {
                        business.method1(i);
                    }
                }
            }).start();
            for(int i=0;i<20;i++){
                business.method2(i);
            }
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
        for (int j=0;j<10;j++){
            System.out.println("method1 task ---"+(j+1)+"第"+(i+1)+"次子线程");
        }
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
        for (int j=0;j<5;j++){
            System.out.println("method2 task ---"+(j+1)+"第"+(i+1)+"次主线程");
        }
        try {
            blockingQueue1.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}