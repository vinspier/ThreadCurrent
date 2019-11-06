package Thread.executors.RSS;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ClassName: NewsBuffer
 * @Description:
 * 通用模块
 * 消息的中间存储结构
 * @Author:
 * @Date: 2019/11/6 17:18
 * @Version V1.0
 **/
public class NewsBuffer {

    /**
     * 阻塞队列 用户存放任务
     * */
    private LinkedBlockingDeque<News> buffer;

    /**
     * 存放历史任务的标识信息
     * KEY: ID VALUE:TAG
     */
    private ConcurrentHashMap<String ,Object> historyItems;


    public NewsBuffer() {
        buffer = new LinkedBlockingDeque<>();
        historyItems = new ConcurrentHashMap<>();
    }

    public void push(News news){
        historyItems.compute(news.getId(),(id,oldTag)->{
            if (oldTag == null){
                buffer.add(news);
                return news.getTag();
            }else{
                System.out.println("News: " + news.getId() + " has been processed already");
                return oldTag;
            }
        });
    }

    public News get() throws InterruptedException{
       return buffer.take();
    }

}
