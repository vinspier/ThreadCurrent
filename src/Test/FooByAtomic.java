package Test;

import java.util.concurrent.atomic.AtomicInteger;

public class FooByAtomic {

    private AtomicInteger firstJob = new AtomicInteger(0);
    private AtomicInteger secondJob = new AtomicInteger(0);

    public FooByAtomic() {

    }

    public static void main(String[] args) {
        FooByAtomic foo = new FooByAtomic();
        new Thread(() -> {
            try {
                foo.first(() -> {
                    System.out.println("first");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.second(() -> {
                    System.out.println("second");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                foo.third(() -> {
                    System.out.println("third");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstJob.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJob.get() != 1) {
            // printSecond.run() outputs "second". Do not change or remove this line.
        }
        printSecond.run();
        secondJob.incrementAndGet();

    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJob.get() != 1) {
            // printThird.run() outputs "third". Do not change or remove this line.
        }
        printThird.run();
        secondJob.incrementAndGet();
    }
}
