package Test;

/**
 * Created by Administrator on 2018/3/31 0031.
 */
public class Test1 {
    public static void main(String[] args) {
        PersonService person = new Person();
        PersonHandler handler = new PersonHandler();
        PersonService person1 = (PersonService)handler.bind(person);
        person1.say("woshi dashabi");

        PersonStatic personStatic = new PersonStatic();
        personStatic.say("----------------------");

        /**
         * 两次的hashcode不一致
         * */
        System.out.println(new Object().hashCode());
        System.out.println(new Object().hashCode());
    }
}
