package Thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {

    public static ExecutorService service = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
       // service.execute(() -> System.out.println(1));
        for (int i = 0; i < 100; i++){
            final int index = i;
            service.submit(() -> {
                try {
                    Thread.sleep(5000);
                    System.out.println(index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void test1(){

    }

    public static void test(){
        ExecutorService service = Executors.newFixedThreadPool(5);//创建固定大小的线程池
        ExecutorService service1 = Executors.newSingleThreadExecutor();//创建单一线程池（线程死了 会自动重新启动)
        ExecutorService service2 = Executors.newCachedThreadPool();// 创建缓存线程池
        for(int i=0;i<10;i++){
            service2.execute(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<3;j++){
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "loop of ---" + j);
                    }
                }
            });
        }
        service2.shutdown(); //任务全部完成后关闭
        // service2.shutdownNow(); 只执行一遍就停止             service2.shutdown(); 任务全部完成后关闭
        // service2.shutdownNow(); 只执行一遍就停止
        System.out.println("----------------------------");

        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        System.out.println();
                                                                    }
                                                                },
                6,
                2,
                TimeUnit.SECONDS);
    }
}
