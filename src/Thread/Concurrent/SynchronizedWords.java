package Thread.Concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedWords {

    private static Map<String,Integer> map = new HashMap<>();


    static {
        map.put("1",1);
        map.put("2",2);
    }

    /**
     * 模拟插入数据 需要4s
     * */
    public synchronized void put(String key,Integer val){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key,val);
        System.out.println("put data {key:" + key + ",val: "+ val + "}");
    }

    public synchronized void get(String key){
        Integer val = map.get(key);
        System.out.println("get data {key:" + key + ",val: "+ val + "}");
    }

    public static void main(String[] args) {
        SynchronizedWords synchronizedWords = new SynchronizedWords();
        ExecutorService executorService = Executors.newCachedThreadPool();


        // 此时 同步加锁 加在的是synchronizedWords实例对象上
        executorService.execute(() -> {
            System.out.println("写线程准备写入数据 key=fxb val=23");
            synchronizedWords.put("fxb",23);
        });

        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("读线程准备读取数据 key=fxb");
            // 读比写慢1s启动 由于写操作需要4s完成
            // 所以 读线程启动1s后 等待数据获取的时间约为 4s - 1s
            synchronizedWords.get("fxb");
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

}
