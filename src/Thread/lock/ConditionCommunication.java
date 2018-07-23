package Thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication {
    private final static CommonBusiness commonBusiness = new CommonBusiness();

    public static void main(String[] args) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<20;i++){
                        commonBusiness.method1(i);
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<20;i++){
                        commonBusiness.method2(i);
                    }
                }
            }).start();
        }
}
class CommonBusiness{
    private Boolean aBoolean = true;
    private Lock lock = new ReentrantLock();
    //lock下的condition实现 条件阻塞
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();

    public void method1(int i){
        lock.lock();
        try {
        while(!aBoolean) {
            try {
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            for (int j = 0; j < 5; j++) {
                System.out.println("method1 task:" + j + "   loop of: " + i);
            }
            aBoolean = false;
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void method2(int i){
        lock.lock();
        try {
            while(aBoolean) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 4; j++) {
                System.out.println("method2 task:" + j + "   loop of: " + i);
            }
            aBoolean = true;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}
