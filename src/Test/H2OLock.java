package Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class H2OLock {

    private volatile int hNum = 0;
    private final ReentrantLock lock = new ReentrantLock();
    // O条件
    private final Condition conditionO = lock.newCondition();
    // H条件
    private final Condition conditionH = lock.newCondition();

    public H2OLock(){

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            // H 输出2个时等待 唤醒O输出
            while (hNum == 2){
                conditionH.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            // H 输出 个数++ 直到打印出2个
            hNum++;
            // 唤起 打印O线程
            if (hNum == 2){
                conditionO.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            // H还没输出2个时 O一直等待
            while (hNum != 2){
                conditionO.await();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            hNum = 0;
            // O输出 唤醒H输出
            conditionH.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
