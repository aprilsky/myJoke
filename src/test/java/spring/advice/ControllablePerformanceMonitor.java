package spring.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import spring.aop.PerformanceHander;

import java.util.Map;

/**
 * @Author: caoxiao
 * @Date: 13-5-26 下午3:13
 */
public class ControllablePerformanceMonitor extends DelegatingIntroductionInterceptor implements Monitorable{
    private ThreadLocal<Boolean> monitorMap = new ThreadLocal<Boolean>();
    public void setMonitorActive(boolean active) {
        monitorMap.set(active);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object obj = null;
        if(monitorMap.get()!=null && monitorMap.get()){
            PerformanceHander.start(mi.getMethod().getName());
            obj = super.invoke(mi);
            PerformanceHander.start(mi.getMethod().getName());
        }
        return obj;
    }
}
