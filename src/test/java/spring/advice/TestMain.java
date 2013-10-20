package spring.advice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: caoxiao
 * @Date: 13-5-21 下午2:51
 */
public class TestMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("beans.xml");
       /* NaiveWaiter waiter = applicationContext.getBean("naiveWaiter",NaiveWaiter.class);
        waiter.greetTo("Tom");
        NaiveWaiter waiter  = (NaiveWaiter) applicationContext.getBean("proxyFactoryBean");
        waiter.greetTo("Tom");*/

    }
}
