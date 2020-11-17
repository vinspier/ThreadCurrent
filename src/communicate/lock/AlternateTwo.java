package communicate.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock锁 进行同步两个线程通信
 */
public class AlternateTwo {

    private Lock lock = new ReentrantLock();

    private static int index = 1;

    public static void main(String[] args) {
        AlternateTwo alternateTwo = new AlternateTwo();
        alternateTwo.alterTwo();
    }

    public void alterTwo(){
        // odd线程
        new Thread(() -> {
            while (index < 100){
                lock.lock();
                try {
                    if ((index & 1) == 1){
                        System.out.println("odd thread and current index is " + index);
                        ++index;
                    }
                }finally {
                    lock.unlock();
                }
            }
        }).start();
        // even线程
        new Thread(() -> {
            while (index < 100){
                lock.lock();
                try {
                    if ((index & 1) == 0){
                        System.out.println("even thread and current index is " + index);
                        ++index;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

}
