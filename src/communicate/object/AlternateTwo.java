package communicate.object;

/**
 * 使用Object的await() 和 notify来通信
 */
public class AlternateTwo {


    /**
     * 多个线程共享的数据项
     */
    private static int index = 1;

    /**
     * 通信使用的对象
     */
    private static final String ODD = "ODD";

    public static void main(String[] args) {
        AlternateTwo alternateTwo = new AlternateTwo();
        alternateTwo.alternateTwo();
    }

    /**
     * 两个线程交替打印
     * Object的wait和notify 必须是建立在有锁的情况下的
     */
    public void alternateTwo(){
        // odd线程
        new Thread(() -> {
            while (index < 100){
                synchronized (ODD){
                    if ((index & 1) == 0){
                        try {
                            ODD.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    System.out.println("odd thread and current index is " + index);
                    ++index;
                }
            }
        }).start();
        // even线程
        new Thread(() -> {
            while (index < 100){
                synchronized (ODD){
                    if ((index & 1) == 0){
                        System.out.println("even thread and current index is " + index);
                        ++index;
                        ODD.notify();
                    }
                }
            }
        }).start();
    }


}
