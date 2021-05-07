package Thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程之间交互
 *
 * @author fxb
 * @date 2021/4/21 3:42 下午
 **/
public class TwoConditionCommunication {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition increment = lock.newCondition();
    public static Condition decrement = lock.newCondition();

    public static long count = 0;

    public static void main(String[] args) {
        TwoConditionCommunication communication = new TwoConditionCommunication();
        communication.increment();
        communication.decrement();
    }

    /**
     * 生产
     * */
    public void increment(){
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(2000);
                    lock.lock();
                    if (count >= 10){
                        decrement.signal();
                    }
                    increment.await();
                    System.out.println("current count is " + count + " after add 1 to count is " + (++count));
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    /**
     * 消费
     * */
    public void decrement(){
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1000);
                    lock.lock();
                    if (count < 10){
                        increment.signal();
                    }
                    decrement.await();
                    System.out.println("current count is " + count + " after dec 1 to count is " + (--count));
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

}
