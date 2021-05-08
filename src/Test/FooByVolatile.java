package Test;

public class FooByVolatile {

    public volatile int state = 1;

    public FooByVolatile() {

    }

    public static void main(String[] args) {
        FooByVolatile foo = new FooByVolatile();
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
        for (;;){
            int stateCopy = this.state;
            if (stateCopy == 1){
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();//
                state = 2;
                break;
            }
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        for (;;) {
            int stateCopy = this.state;
            if (stateCopy == 2) {
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                state = 3;
                break;
            }
        }

    }

    public void third(Runnable printThird) throws InterruptedException {
        for (;;) {
            int stateCopy = this.state;
            if (stateCopy == 3) {
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
                state = 1;
                break;
            }
        }
    }
}
