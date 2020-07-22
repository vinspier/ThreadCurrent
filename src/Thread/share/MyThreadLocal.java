package Thread.share;

import java.util.Random;

/**
 * ThreadLocal 为当前线程创建副本变量
 * 以当前线程 最为key
 *
 * */
public class MyThreadLocal {
    private static ThreadLocal<ThreadShareData> threadLocal = new ThreadLocal<ThreadShareData>();
    public static void main(String[] args) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    ThreadShareData.getThreadShareData().setName("fxb" + data);
                    ThreadShareData.getThreadShareData().setAge(data);
                    new A().get();
                }
            }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int data = new Random().nextInt();
                ThreadShareData.getThreadShareData().setName("fxb" + data);
                ThreadShareData.getThreadShareData().setAge(data);
                new B().get();
            }
        }).start();
    }
    static class A{
        public void get(){
/*            ThreadShareData shareData = threadLocal.get();
            System.out.println("thread:" + Thread.currentThread().getName() +"---"+ shareData.getName() +"----"+shareData.getAge());*/
            ThreadShareData shareData = ThreadShareData.getThreadShareData();
            System.out.println("thread:" + Thread.currentThread().getName() +"---"+ shareData.getName() +"---模块A----"+shareData.getAge());
        }
    }
    static class B{
        public void get(){
/*            ThreadShareData shareData = threadLocal.get();
            System.out.println("thread:" + Thread.currentThread().getName() +"---"+ shareData.getName() +"----"+shareData.getAge());*/
            ThreadShareData shareData = ThreadShareData.getThreadShareData();
            System.out.println("thread:" + Thread.currentThread().getName() +"---"+ shareData.getName() +"---模块B----"+shareData.getAge());
        }
    }
}

class ThreadShareData{
    private String name;
    private int age;
    // 实现线程范围内的共享数据
    // 相当于一个map 将共享数据存储起来 使各自的线程能够访问其对应的共享数据
    private static ThreadLocal<ThreadShareData> shareDataThreadLocal = new ThreadLocal<>();

    public ThreadShareData() {
    }

    public static ThreadShareData getThreadShareData(){
        ThreadShareData threadShareData = shareDataThreadLocal.get();
        if(threadShareData == null){
            threadShareData = new ThreadShareData();
            shareDataThreadLocal.set(threadShareData);
        }
        return threadShareData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}