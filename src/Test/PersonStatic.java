package Test;

/**
 * Created by Administrator on 2018/4/11.
 */
public class PersonStatic implements PersonService{
    @Override
    public void say(String s) {
        System.out.println("before");
        new Person().say(s);
        System.out.println("after");
    }
}
