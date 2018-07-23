package Thread.Concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExchanger {
    static ExecutorService executorService = Executors.newCachedThreadPool();
    static Exchanger exchanger = new Exchanger();

    public static void main(String[] args) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = "fxb1";
                    System.out.println(Thread.currentThread().getName()+"准备把"+data1+"换出去");
                    String data2 = (String) exchanger.exchange(data1);
                    System.out.println(Thread.currentThread().getName()+"换来数据"+data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data2 = "fxb2";
                    System.out.println(Thread.currentThread().getName()+"准备把"+data2+"换出去");
                    String data1 = (String)exchanger.exchange(data2);
                    System.out.println(Thread.currentThread().getName()+"换来数据"+data1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
