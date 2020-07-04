package customizeThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        ExecutorService service = new CustomizeThreadPoolExecutor(7,7,
                0, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(),new CustomizeThreadFactory());

//        service.execute(() -> {
//            System.out.println("实际真实业务逻辑");
//        });

        service.execute(() -> {
            System.out.println("实际真实业务逻辑");
            int a = 1/0;
        });

        service.shutdown();
    }

}
