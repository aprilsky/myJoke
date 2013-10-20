package spring.aop;

import net.sf.cglib.proxy.Enhancer;
import spring.aop.cglib.ForumServiceNoInterfaceProxy;

import java.lang.reflect.Proxy;

/**
 * @Author: caoxiao
 * @Date: 13-5-19 下午6:06
 */
public class TestMain {
    public static void main(String[] args) {
        //testAopForJdk();
        testForCgLib();
    }

     static void testForCgLib(){
         ForumServiceNoInterface forumService = ForumServiceNoInterfaceProxy.getInstance();
         forumService.remove(1);
     }

    static void testAopForJdk(){
        ForumAPI target = new ForumService();
        PerformanceInvocation invocation = new PerformanceInvocation(target);
        ForumAPI forumService = (ForumAPI) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), invocation);
        forumService.remove(1);
    }
}
