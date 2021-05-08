package Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * 按顺序交替打印 0-奇数-偶数
 * 直到打印到n为止
 */
public class ZeroEvenOdd {

    private int n;

    private volatile int zero = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);

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

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++){
            while (zero != 0){
                Thread.yield();
            }
            printNumber.accept(0);
//            if (i % 2 == 0) {
//                zero = 1;
//            } else {
//                zero = 2;
//            }
            zero = i % 2 + 1;
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        // 小于n 并且为奇数时 等待
        for (int i = 2; i <= n; i += 2){
            while (zero != 2){
                Thread.yield();
            }
            printNumber.accept(i);
            zero = 0;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        // 小于n 并且为偶数时等待
        for (int i = 1; i <= n; i += 2){
            while (zero != 1){
                Thread.yield();
            }
            printNumber.accept(i);
            zero = 0;
        }
    }
}
