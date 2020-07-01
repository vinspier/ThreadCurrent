package Thread.keyWord;

/**
 *
 * 1、作用于给定对象加锁
 *
 * 2、作用于实例方法，相当于给当前实例对象加锁 对象锁
 *
 * 3、作用于静态方法，相当于给当前类加锁 类锁
 *
 * */
public class SynchronizedKeyWord {

    public final static SynchronizedKeyWord KEY_WORDS = new SynchronizedKeyWord();

    public static Long count = 0L;

    public synchronized void methodA(){
        System.out.println("synchronized but not static method A");
        count++;
    }

    public synchronized void methodB(){
        System.out.println("synchronized but not static method B");
        count++;
    }

    public void methodC(){
        System.out.println("not synchronized and not static method C");
        count++;
    }

    public synchronized static void methodStaticA(){
        System.out.println("synchronized and static method A");
        count++;
    }

    public synchronized static void methodStaticB(){
        System.out.println("synchronized and static method B");
        count++;
    }

    public static void methodStaticC(){
        System.out.println("not synchronized but static method C");
        count++;
    }

    public Long getCount() {
        return count;
    }
}
