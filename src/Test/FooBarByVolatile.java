package Test;

public class FooBarByVolatile {
    private int n;

    private volatile boolean state = true;

    public FooBarByVolatile(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        FooBarByVolatile fooBar = new FooBarByVolatile(5);
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
            while (!state){
                // 让出cpu
                Thread.yield();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            state = false;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
           while (state){
               // 让出cpu
               Thread.yield();
           }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            state = true;
        }
    }

}
