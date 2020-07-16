package com.yss.acs;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**没有实现接口，需要CGlib动态代理的目标类
 * 使用CGLib实现动态代理，完全不受代理类必须实现接口的限制，而且CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类，比使用Java反射效率要高。
 * 唯一需要注意的是，CGLib不能对声明为final的方法进行代理，因为CGLib原理是动态生成被代理类的子类。
 * @author tan
 * @create 2020-07-16
 */
public class TargetObject {
    public String method1(String paramName) {
        return paramName;
    }

    public int method2(int count) {
        return count;
    }

    public int method3(int count) {
        return count;
    }

    @Override
    public String toString() {
        return "TargetObject []" + getClass();
    }

}

/**
 * 目标对象拦截器,在调用目标方法时,cglib会回调MethodInterceptor接口拦截,来实现自己的代理逻辑
 */
class TargetInterceptor implements MethodInterceptor{

    /**
     *
     * @param targetObj   目标对象
     * @param method    目标方法
     * @param params    参数
     * @param methodProxy  cglib方法代理对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object targetObj, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法被调用");
        return methodProxy.invokeSuper(targetObj,params);
    }
}


class TestCglib{
    public static void main(String[] args) {
        /*
        首先将被代理类TargetObject设置成父类，
        然后设置拦截器TargetInterceptor，
        最后执行enhancer.create()动态生成一个代理类，
        并从Object强制转型成父类型TargetObject。
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetObject.class);
        enhancer.setCallback(new TargetInterceptor());
        TargetObject targetObject = (TargetObject)enhancer.create();*/

        TargetObject targetObject = (TargetObject)Enhancer.create(TargetObject.class, new TargetInterceptor());
        System.out.println(targetObject.method1("张三"));
        System.out.println(targetObject.method2(1));
    }
}
