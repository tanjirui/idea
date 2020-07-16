package com.yss.acs;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**动态代理测试类
 * @author tan
 * @create 2020-07-16
 */
public class DymicProxyTest {

    public static void main(String[] args) {
        Animal animal = new Dog();
        Animal an = (Animal) Proxy.newProxyInstance(animal.getClass().getClassLoader(), animal.getClass().getInterfaces(), new DynamicProxy(new Dog()));
        an.eat();
    }
}

interface Animal{
    void eat();
}

class Dog implements Animal{

    @Override
    public void eat() {
        System.out.println("小狗吃东西!!");
    }
}

//创建代理对象
class DynamicProxy implements InvocationHandler{

    //代理对象
    private Object targetObj;

    public DynamicProxy(Object targetObj) {
        this.targetObj = targetObj;
    }

    //被代理的类实例
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        //执行代理方法
        Object invoke = method.invoke(targetObj, args);
        System.out.println("after");
        return invoke;
    }
}
