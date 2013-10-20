package spring.aop;

/**
 * @Author: caoxiao
 * @Date: 13-5-19 下午5:57
 */
public class PerformanceHander {
    private static ThreadLocal<Performance> performanceThreadLocal = new ThreadLocal<Performance>() ;
    public static void start(String method){
        Performance performance = new Performance(method);
        performanceThreadLocal.set(performance);
    }

    public static void end(String method){
        Performance performance = performanceThreadLocal.get();
        performance.printPerformanceResult();
    }
}
