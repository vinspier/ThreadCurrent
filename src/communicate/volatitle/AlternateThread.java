package communicate.volatitle;

/**
 * 使用 volatile 修饰变量
 *
 */
public class AlternateThread {

    /**
     * volatile修饰
     * 线程可见性
     * 顺序性
     * 变量本身不依赖于其他变量
     *
     */
    private static volatile boolean ODD = true;
    private static int INDEX = 1;

    /**
     * ++COUNT 非原子性
     * 执行引擎看不到
     * 所以 即使用volatile修饰 多线程下还是会错误
     */
    private static volatile int COUNT = 0;

    public static void main(String[] args) {
        AlternateThread thread = new AlternateThread();
        thread.alternateTwo();
//        thread.twoAddThread();

    }

    /**
     * 两个线程交替打印
     */
    public void alternateTwo(){
        // 打印奇数 odd
        new Thread(() -> {
            while (INDEX < 100){
                if (ODD){
                    System.out.println("线程1-" + INDEX);
                    ++INDEX;
                    ODD = false;
                }
            }
        }).start();
        // 打印偶数 even
        new Thread(() -> {
            while (INDEX < 100){
                if (!ODD){
                    System.out.println("线程2-" + INDEX);
                    ++INDEX;
                    ODD = true;
                }
            }
        }).start();
    }

    public void twoAddThread(){
        new Thread(() -> {
           for (int i = 0; i < 10000; i++){
               ++COUNT;
           }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++){
                ++COUNT;
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++){
                ++COUNT;
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10000; i++){
                ++COUNT;
            }
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("current count value is " + COUNT);
    }

}
