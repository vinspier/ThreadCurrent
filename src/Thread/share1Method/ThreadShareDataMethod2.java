package Thread.share1Method;

public class ThreadShareDataMethod2 {
    public static void main(String[] args) {
        ShareData1 shareData1 = new ShareData1();
        /****************************************
        将共享数据封装在 ShareData1 对象里 然后将该对象一一传递给各个Runnable对象
         每个线程对共享数据的操作方法也分配到各个runnable对象里 易实现互斥和通信
         （共享资源shareData1 是 “塞” 给 runnable的）
        *****************************************/
        new Thread(new Increment(shareData1)).start();
        new Thread(new Decrement(shareData1)).start();
    }
}

class Increment implements Runnable{
    private ShareData1 shareData1;
    public Increment(ShareData1 shareData1){
        this.shareData1 = shareData1;
    }
    @Override
    public void run(){
        shareData1.increment();
    }
}

class Decrement implements Runnable{
    private ShareData1 shareData1;
    public Decrement(ShareData1 shareData1){
        this.shareData1 = shareData1;
    }
    @Override
    public void run(){
        shareData1.decrement();
    }
}

class ShareData1{
    private static int data = 50;

    public synchronized void increment(){
        data++;
        System.out.println(data);
    }

    public synchronized void decrement(){
        data--;
        System.out.println(data);
    }
}