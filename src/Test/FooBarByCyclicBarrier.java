package Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooBarByCyclicBarrier {
    private int n;

    CyclicBarrier cb = new CyclicBarrier(2);
    volatile boolean fin = true;

    public FooBarByCyclicBarrier(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        FooBarByCyclicBarrier fooBar = new FooBarByCyclicBarrier(5);
        new Thread(() -> {
            try {
                fooBar.foo(() -> {
                    System.out.print("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                fooBar.bar(() -> {
                    System.out.print("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!fin){

            }
            fin = false;
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // 直到上一次foo打印 执行cb.await()
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fin = true;
        }
    }
}
