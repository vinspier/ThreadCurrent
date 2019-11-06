package Thread.executors.RSS;

/**
 * 消息的消费者
 */
public class NewsConsumer implements Runnable{

    private NewsBuffer buffer;

    public NewsConsumer(NewsBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                News news = buffer.get();
                System.out.println("news :" + news.getId() + " is consuming\n" + "do something else in this method what you want ");
            } catch (InterruptedException e) {

            }
        }
    }
}
