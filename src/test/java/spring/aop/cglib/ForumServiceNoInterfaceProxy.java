package spring.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import spring.aop.ForumServiceNoInterface;

/**
 * @Author: caoxiao
 * @Date: 13-5-20 下午12:31
 */
public class ForumServiceNoInterfaceProxy {
    public static ForumServiceNoInterface getInstance(){
        Enhancer en = new Enhancer();
        // 设置要代理的目标类
        en.setSuperclass(ForumServiceNoInterface.class);
        // 设置要代理的拦截器
        en.setCallback(new MyMethodInterceptor());
        // 生成代理类的实例
        return (ForumServiceNoInterface)en.create();
    }
    private ForumServiceNoInterfaceProxy(){

    }
}
