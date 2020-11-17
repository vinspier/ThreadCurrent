package communicate.concurrentUtil;

import communicate.lock.AlternateTwo;

import java.util.concurrent.Semaphore;

/**
 * 使用信号量 完成两个线程之间的通信
 */
public class AlternateTwoSemaphore {

    private int index = 1;

    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        AlternateTwo alternateTwo = new AlternateTwo();
        alternateTwo.alterTwo();
    }

    public void alterTwo(){
        // odd线程
        new Thread(() -> {
            while (index < 100){
                semaphore.tryAcquire();
                try {
                    if ((index & 1) == 1){
                        System.out.println("odd thread and current index is " + index);
                        ++index;
                    }
                }finally {
                    semaphore.release();
                }
            }
        }).start();
        // even线程
        new Thread(() -> {
            while (index < 100){
                semaphore.tryAcquire();
                try {
                    if ((index & 1) == 0){
                        System.out.println("even thread and current index is " + index);
                        ++index;
                    }
                } finally {
                    semaphore.release();
                }
            }
        }).start();
    }

}
