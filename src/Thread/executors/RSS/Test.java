package Thread.executors.RSS;

public class Test {
    public static void main(String[] args) {
        NewsSystem newsSystem = new NewsSystem(10);
        Thread t = new Thread(newsSystem);
        t.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newsSystem.shutdown();
        t.interrupt();
    }
}
