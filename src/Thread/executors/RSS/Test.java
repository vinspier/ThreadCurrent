package Thread.executors.RSS;

public class Test {
    public static void main(String[] args) {
        NewsSystem newsSystem = new NewsSystem(10);
        new Thread(newsSystem).start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newsSystem.shutdown();
        Thread.currentThread().interrupt();
    }
}
