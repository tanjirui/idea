package com.yss.acs;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author tan
 * @create 2020-07-16
 */
public class CGLibTest {
    public static void main(String[] args) {
        Fruit fruit = new Apple();
        Fruit o = (Fruit)Enhancer.create(fruit.getClass(), new CGLIBProxy());
        o.good();
    }
}

interface Fruit{
    void good();
}

class Apple implements Fruit{
    @Override
    public void good() {
        System.out.println("苹果很香哟!");
    }
}

class CGLIBProxy implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("加在业务之前的处理");
        return methodProxy.invokeSuper(o,args);
    }
}