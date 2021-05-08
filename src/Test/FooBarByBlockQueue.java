package Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooBarByBlockQueue {
    private int n;
    private BlockingQueue<Integer> queueFoo = new LinkedBlockingQueue<>(1);
    private BlockingQueue<Integer> queueBar = new LinkedBlockingQueue<>(1);

    public FooBarByBlockQueue(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        FooBarByBlockQueue fooBar = new FooBarByBlockQueue(5);
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
            queueFoo.put(i);
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            queueBar.put(i);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            queueBar.take();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            queueFoo.take();
        }
    }
}
