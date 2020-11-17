package Thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {
    private static PrintName printName = new PrintName();
    public static void main(String[] args) {
        new MyLock().init(printName);
    }

    public void init(final PrintName printName){
        new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printName.printName0("fxb1");
            }
        }).start();

        new Thread(() -> {
            while(true){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printName.printName0("fxb2");
            }
        }).start();
    }

    static class PrintName {
        Lock lock = new ReentrantLock();
        // 使用sychronized 实现互斥
        public synchronized void printName0(String name){
            System.out.println(name);
            System.out.println("----------------");
        }

        //使用lock锁
        public void printName(String name){
            lock.lock();
            try {
                System.out.println(name);
                System.out.println("----------------");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}