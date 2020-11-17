package Thread.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁案例
 * 1、竞争资源必须互斥使用
 * 2、竞争资源占有不可被强制释放 由持有者主动释放
 * 3、持有者不释放 其他人不可持有
 * 4、资源行程闭环持有
 */
public class DeadLock {

    /**
     * 锁对象
     */
    private static final String LOCK_ONE = "LOCK1";
    private static final String LOCK_TWO = "LOCK2";

    /**
     * 重入锁
     */
    private Lock lockA = new ReentrantLock();
    private Lock lockB = new ReentrantLock();


    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(() -> {
//            deadLock.produce();
            deadLock.produce1();
        }).start();
        new Thread(() -> {
//            deadLock.consume();
            deadLock.consume1();
        }).start();
    }

    public void produce(){
        synchronized (LOCK_ONE){
            System.out.println("fetch lock one");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK_TWO){
                System.out.println("fetch lock two");
                int times = 50;
                for (;;){
                    times--;
                    if (times < 1){
                        break;
                    }
                }
            }
        }
    }

    public void consume(){
        synchronized (LOCK_TWO){
            System.out.println("fetch lock two");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK_ONE){
                System.out.println("fetch lock one");
                int times = 100;
                for (;;){
                    times--;
                    if (times < 1){
                        break;
                    }
                }
            }
        }
    }

    public void produce1(){
        lockA.lock();
        try {
            System.out.println("fetch lock A");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockB.lock();
            try {
                System.out.println("fetch lock B");
                int times = 100;
                for (;;){
                    times--;
                    if (times < 1){
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockB.unlock();
                System.out.println("release lock B");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockA.unlock();
            System.out.println("release lock A");
        }
    }

    public void consume1(){
        lockB.lock();
        try {
            System.out.println("fetch lock B");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockA.lock();
            try {
                System.out.println("fetch lock A");
                int times = 100;
                for (;;){
                    times--;
                    if (times < 1){
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lockA.unlock();
                System.out.println("release lock A");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockB.unlock();
            System.out.println("release lock B");
        }
    }

    public void produce2(){
        synchronized (LOCK_ONE){
            try {
                System.out.println("fetch lock A");
                LOCK_ONE.wait();
                System.out.println("produce2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用Object 的 wait() notify()
     * 必须要在获得到该对象锁的情况下 进行通信
     *
     */
    public void consume2(){
        synchronized(LOCK_ONE){
            try {
                System.out.println("fetch lock A");
                System.out.println("consume2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LOCK_ONE.notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
