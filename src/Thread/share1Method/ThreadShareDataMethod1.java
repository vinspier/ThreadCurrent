package Thread.share1Method;

public class ThreadShareDataMethod1 {
    private static ShareData shareData = new ShareData();
    public static void main(String[] args) {
        /* ************************
        各个runnable作为ThreadShareDataMethod1该外部类的内部类，shareData作为该外部类的成员变量
        每个线程对共享资源的操作方法有 外部类的成员变量shareData去访问
        ************************ */
        new Thread(new Runnable() {
            @Override
            public void run() {
                shareData.decrement();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                shareData.increment();
            }
        }).start();
    }
}

class ShareData{
    private static int data = 50;

    public synchronized void increment(){
        data++;
        System.out.println("执行完加操作后"+data);
    }

    public synchronized void decrement(){
        data--;
        System.out.println("执行完减操作后"+data);
    }
}