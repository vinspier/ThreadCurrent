package Thread.lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyReadWriteLock {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final ShareData shareData = new ShareData();

        executorService.execute(() -> {
            while(true){
                // 每 2.5s 读取数据
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                shareData.write(new Random().nextInt(100));
            }
        });

//        for(int i=0;i<2;i++){
//            executorService.execute(() -> {
//                while (true){
//                    // 每 1s 读取数据
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    shareData.get();
//                }
//            });
//        }
    }
}

//共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
class ShareData{
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Object data = null;

    /**
     * 写数据 读线程被阻塞
     * */
    public void write(Object object){
        readWriteLock.writeLock().lock();
        try {
           //  System.out.println("共享数据准备写入");
            data = object;
            System.out.println("共享数据"+data+"已经写入");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * 多个读线程不阻塞
     * */
    public void get(){
        readWriteLock.readLock().lock();
        try {
            // System.out.println(Thread.currentThread().getName()+"准备读取数据");
            System.out.println(Thread.currentThread().getName() + "读取数据成功-----" +data);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}