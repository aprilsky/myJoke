package spring.aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import spring.aop.PerformanceHander;

import java.lang.reflect.Method;

/**
 * @Author: caoxiao
 * @Date: 13-5-20 下午12:27
 */
public class MyMethodInterceptor implements MethodInterceptor {
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("通过cgLib代理");
        PerformanceHander.start(method.getName());
        // 执行目标方法，并保存目标方法执行后的返回值
        Object rvt = proxy.invokeSuper(obj, new Object[]{4});
        PerformanceHander.end(method.getName());
        return rvt;
    }
}
