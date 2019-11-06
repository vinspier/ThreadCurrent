package Thread.executors.RSS;

import java.util.UUID;

/**
 * 消息的生产
 */
public class NewsProduce implements Runnable{

    /**
     * 生产的总次数
     */
    private final int times;

    private final NewsBuffer buffer;

    public NewsProduce(int times, NewsBuffer buffer) {
        this.times = times;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("start produce news");
        for (int i = 0;i < times;i++){
            News news = new News();
            news.setId(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
            news.setTag(UUID.randomUUID().toString().replaceAll("-","").substring(0,5));
            news.setContent(UUID.randomUUID().toString().replaceAll("-",""));
            news.setSource(UUID.randomUUID().toString().replaceAll("-",""));
            news.setTitle(UUID.randomUUID().toString().replaceAll("-",""));
            buffer.push(news);
        }
    }
}
