import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


interface ProductService {
    public void getProductInfo();
}

class ProductServiceImpl implements ProductService {
    @Override
    public void getProductInfo() {
        System.out.println("获取到产品信息为小鸡");
    }
}

/**
 * 代理模式
 * 1.静态代理
 * 2.动态代理
 * 3.cglib代理
 *
 * @author tan
 * @create 2020-07-16
 */
public class ProxyDemo {

    //正常调用
    @Test
    public void test() {
        ProductService productService = new ProductServiceImpl();
        productService.getProductInfo();
    }

    //静态代理测试
    @Test
    public void test01() {
        ProductService productService = new staticProxy(new ProductServiceImpl());
        productService.getProductInfo();
    }

    //动态代理测试
    @Test
    public void test02() {
        ProductService productService = new ProductServiceImpl();
        ProductService ps = (ProductService) Proxy.newProxyInstance(productService.getClass().getClassLoader(), productService.getClass().getInterfaces(), new DymicProxy(productService));
        ps.getProductInfo();
    }


    //cglib代理
    @Test
    public void test03() {
        ProductService productService = new ProductServiceImpl();

        ProductService ps = (ProductService) Enhancer.create(productService.getClass(), new cglibProxy());
        ps.getProductInfo();
    }


}

/**
 * 静态代理
 */
class staticProxy implements ProductService {

    // 在类中维护一个代理对象，通过构造器初始化代理对象
    private ProductService productService;

    public staticProxy(ProductService productService) {
        this.productService = productService;
    }

    // 实现接口方法，并在接口方法中使用代理对象调用同一接口方法，并实现前后拦截等功能。
    @Override
    public void getProductInfo() {
        check();
        productService.getProductInfo();
    }

    public void check() {
        System.out.println("静态代理模式");
    }
}

/**
 * 动态代理
 */
class DymicProxy implements InvocationHandler {

    private ProductService productService;

    public DymicProxy(ProductService productService) {
        this.productService = productService;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        check();
        return method.invoke(productService, args);
    }

    public void check() {
        System.out.println("动态代理");
    }

}

/**
 * cglib代理, 目标对象拦截器
 */
class cglibProxy implements MethodInterceptor {

    /**
     * 重写方法拦截在方法前和方法后加入业务
     * @param proxy 目标对象
     * @param method 目标方法
     * @param args 参数
     * @param methodProxy   cglib方法代理对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        check();
        return methodProxy.invokeSuper(proxy,args);
    }

    public void check() {
        System.out.println("cglib代理");
    }

}