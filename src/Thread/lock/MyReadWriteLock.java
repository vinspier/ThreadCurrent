package Thread.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReadWriteLock {
    public static void main(String[] args) {
        final ShareData shareData = new ShareData();
        for(int i=0;i<4;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    shareData.get();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        shareData.write(new Random().nextInt(100));
                    }
                }
            }).start();
        }
    }
}

class ShareData{
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Object data = null;
    //共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。

    public void write(Object object){
        readWriteLock.writeLock().lock();
        try {
            System.out.println("共享数据准备写入");
            Thread.sleep(4000);
            data = object;
            System.out.println("共享数据"+data+"已经写入");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    public void get(){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"准备读取数据");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "读取数据成功-----" +data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}