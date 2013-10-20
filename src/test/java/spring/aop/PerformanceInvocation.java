package spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: caoxiao
 * @Date: 13-5-19 下午6:08
 */
public class PerformanceInvocation implements InvocationHandler {
    private Object target;
    public PerformanceInvocation(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("通过JDK代理");
        for (Object arg : args) {
            System.out.println(arg);
        }
        PerformanceHander.start(method.getName());
        Object obj = method.invoke(target,args);
        PerformanceHander.end(method.getName());
        return obj;
    }
}
