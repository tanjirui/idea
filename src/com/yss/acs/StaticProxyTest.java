package com.yss.acs;

/**静态代理测试类
 * 静态代理的原理比较简单：
 ①创建代理类实现代理接口
 ②在类中维护一个代理对象，通过构造器初始化代理对象
 ③实现接口方法，并在接口方法中使用代理对象调用同一接口方法，并实现前后拦截等功能。
 * @author tan
 * @create 2020-07-16
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        //获取student对象的代理
        PersionProxy persionProxy = new PersionProxy(new Student("张三"));
        //代理执行方法
        persionProxy.pay();
    }
}

//persion接口
interface Persion{
    void pay();
}

class Student implements Persion{
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void pay() {
        System.out.println(name+" pay!");
    }
}


//创建代理类实现代理接口
class PersionProxy implements Persion{

    // 在类中维护一个代理对象，通过构造器初始化代理对象
    private Persion persion;

    public PersionProxy(Persion persion) {
        this.persion = persion;
    }

    // 实现接口方法，并在接口方法中使用代理对象调用同一接口方法，并实现前后拦截等功能。
    @Override
    public void pay() {
        System.out.println("before");
        persion.pay();
        System.out.println("after");
    }
}

/**
 *由代码可知静态代理是写死的代码，每个代理只能代理一个类型的指定方法。那么它的缺点也显而易见了，
 * 如果需要对大量的接口进行代理，就会有大量的代理类，不利于维护；
 * 如果要对代理的接口修改/添加/删除方法，同时也需要维护实现类和代理类，不利于扩展。
 */