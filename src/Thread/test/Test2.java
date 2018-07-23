package Thread.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/11/15 0015.
 */
public class Test2 {
    // 同步队列 只有当 take操作来时 才会进行put操作
    public static void main(String[] args) {
        final SynchronousQueue<String> queue = new SynchronousQueue<>();
        final Semaphore semaphore = new Semaphore(1);
        for(int j=0;j<10;j++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                        try {
                            semaphore.acquire();
                            String input = queue.take();
                            String output = TestDo.doSome(input);
                            System.out.println(Thread.currentThread().getName()+"---"+output);
                            semaphore.release();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }).start();
        }

        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        for(int i=0;i<10;i++) {  //这行不能改动
            String input = i + "";  //这行不能改动
            try {
                queue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

//不能改动此TestDo类
class TestDo {
    public static String doSome(String input){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":"+ (System.currentTimeMillis() / 1000);
        return output;
    }
}
