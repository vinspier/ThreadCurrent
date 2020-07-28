package Thread.Concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileWords {

  //  public static boolean existed = true;
    public static volatile boolean existed = true;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {
                while (true){
                    if (existed){
                        System.out.println(">>>>>>> existed consume <<<<<<<");
                        existed = false;
                    }
                }
            });
            executorService.execute(() -> {
                while (true){
                    if (!existed){
                        System.out.println(">>>>>>> notExisted  produce<<<<<<<");
                        existed = true;
                    }
                }

            });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
