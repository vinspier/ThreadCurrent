package Thread.pool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: TestThreadPool
 * @Description:
 * @Author:
 * @Date: 2020/3/29 12:28
 * @Version V1.0
 **/
public class TestThreadPool {

    private static final ExecutorService service = Executors.newFixedThreadPool(5);//创建固定大小的线程池

    public static void main(String[] args) {
        System.out.println("============= 支付请求 预备调用线程池进行支付 ===================");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(dateFormat.parse("2020-04-31").after(dateFormat.parse("2020-04-30")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
//
//        try {
//            service.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    pay();
//                }
//            });
//        } catch (Exception e) {
//            System.out.println(" ========== 调用线程执行失败 ================");
//        }
//        System.out.println(" ========== 调用线程执行完毕 ================");
    }


    public static void pay(){
        try {
            for (int i= 0; i < 10; i++){
                Thread.sleep(2000);
                System.out.println("+++++++++++ 发起支付 +++++++++++++++++");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
