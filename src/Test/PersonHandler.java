package Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2018/3/31 0031.
 */
public class PersonHandler implements InvocationHandler {
    private Object target;

    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        System.out.println("jdk动态代理");
        //反射方法前调用
        System.out.println("在对象调用实际方法前");
        method.invoke(target,args);
        //反射方法前调后
        System.out.println("在对象调用实际方法后");
        return null;
    }
}
