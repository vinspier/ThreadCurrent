package Thread.keyWord;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSynchronizedKeyWord {

   private static ExecutorService executorService = Executors.newFixedThreadPool(1000);

    public static void main(String[] args) {
       // test1();
       // test12();
        test2();
    }

    /**
     * 同一个实例对象 在不同线程中调用实例同步方法
     *
     * 在多个线程中 锁加在同一个实例对象上 则进入同步方法时 需获取该实例对象的锁
     *
     * 线程安全
     * */
    public static void test1(){
        SynchronizedKeyWord keyWord = new SynchronizedKeyWord();
        for (int i = 0; i < 10000; i++){
            executorService.submit(() -> {
                keyWord.methodA();
            });
        }
        waitTime();
        System.out.println(keyWord.getCount());
    }

    /**
     * 不同实例对象 在不同线程中调用实例同步方法
     *
     * 在进入同步方法时 锁加在了不同的实例上 所以不会发生互斥
     *
     * 存在线程安全问题
     * */
    public static void test12(){
        for (int i = 0; i < 10000; i++){
            executorService.submit(() -> {
                SynchronizedKeyWord keyWord = new SynchronizedKeyWord();
                keyWord.methodA();
            });
        }
        waitTime();
        System.out.println(SynchronizedKeyWord.count);
    }

    /**
     * 不同实例对象 在多线程中调用类静态方法
     *
     * 在进入同步方法时 锁加在了该类上 所以不会发生互斥
     *
     * 线程安全
     * */
    public static void test2(){
        for (int i = 0; i < 10000; i++){
            executorService.submit(() -> {
                SynchronizedKeyWord.methodStaticA();
            });
        }
        waitTime();
        System.out.println(SynchronizedKeyWord.count);
    }

    /**
     *
     * */
    public static void test3(){

    }

    /**
     * 模拟处理逻辑等待一个较长时间
     * */
    public static void waitTime(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
