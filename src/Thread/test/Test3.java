package Thread.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2017/11/16 0016.
 */
public class Test3 extends Thread {
    private TestDo1 testDo1;
    private String key;
    private String value;

    public Test3(String key,String key2,String value){
        this.testDo1 = TestDo1.getInstance();
			/*常量"1"和"1"是同一个对象，下面这行代码就是要用"1"+""的方式产生新的对象，
			以实现内容没有改变，仍然相等（都还为"1"），但对象却不再是同一个的效果*/
        this.key = key+key2;//key ke2 是方法中的变量
        this.value = value;
    }


    public static void main(String[] args) throws InterruptedException{
        Test3 a = new Test3("1","","1");
        Test3 b = new Test3("1","","2");
        Test3 c = new Test3("3","","3");
        Test3 d = new Test3("4","","4");
        Test3 e = new Test3("4","","5");
        System.out.println("begin:"+(System.currentTimeMillis()/1000));
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }

    public void run(){
        testDo1.doSome(key, value);
    }
}

class TestDo1 {

    private TestDo1() {}
    private static TestDo1 _instance = new TestDo1();
    public static TestDo1 getInstance() {
        return _instance;
    }

    //  private ArrayList<String> keys = new ArrayList<>(); 该表 在迭代过程该集合执行不能操作 add（）
    private CopyOnWriteArrayList<Object> keys = new CopyOnWriteArrayList<>();
    public void doSome(Object key, String value) {
        Object object = key;
        if (!keys.contains(object)){
            keys.add(object);
        }
        // contains为true
        else{
            for(Iterator iterator = keys.iterator();iterator.hasNext();){
                Object object1 = iterator.next();
                if(object1.equals(object)){
                    object = object1;
                }
            }
        }
        synchronized (object)
        // 以大括号内的是需要局部同步的代码，不能改动!
        {
            try {
                Thread.sleep(1000);
                System.out.println(key+":"+value + ":"
                        + (System.currentTimeMillis() / 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
