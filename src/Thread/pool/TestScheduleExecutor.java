package Thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: TestScheduleExecutor
 * @Description:
 * @Author:
 * @Date: 2019/11/8 17:06
 * @Version V1.0
 **/
public class TestScheduleExecutor {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("==========开始执行================");
            }
        };
        executorService.scheduleAtFixedRate(runnable,2,5, TimeUnit.SECONDS);
    }
}
