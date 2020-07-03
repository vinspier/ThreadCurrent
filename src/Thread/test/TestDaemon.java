package Thread.test;

public class TestDaemon {

    private static Runnable runnable = () -> {
        while (true){
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) {
       // notDaemonRun();
        daemonRun();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 用户线程 只有当线程中的内容执行完成时 才会停止
    public static void notDaemonRun(){
        Thread t = new Thread(runnable);
        t.start();
    }

    // 守护线程 随着系统的关闭而关闭
    public static void daemonRun(){
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        t.start();
    }

}
