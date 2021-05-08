package Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class ZeroEvenOddByLock {

    private int n;

    // 0-打0 1-打1 2-打2
    private volatile int state = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    public ZeroEvenOddByLock(int n) {
        this.n = n;
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOddByLock zeroEvenOdd = new ZeroEvenOddByLock(11);

        new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

    // 0
    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                if(state != 0){
                    condition1.await();
                }
                printNumber.accept(0);
                // 计算下一次打印位置 1-打印奇数 2-打印偶数
                state = i % 2 + 1;
                if ((state % 2) == 1){
                    condition3.signal();
                }else {
                    condition2.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // 偶数
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            lock.lock();
            try {

                if (state != 2){
                    condition2.await();
                }
                printNumber.accept(i);
                state = 0;
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    // 奇数
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            lock.lock();
            try {
                if (state != 1){
                    condition3.await();
                }
                printNumber.accept(i);
                state = 0;
                condition1.signal();
            } finally {
                lock.unlock();
            }
        }
    }

}
