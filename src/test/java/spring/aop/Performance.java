package spring.aop;

/**
 * @Author: caoxiao
 * @Date: 13-5-19 下午5:58
 */
public class Performance {
    private long start;
    private long end;
    private String method;

    public Performance(String method) {
        this.start = System.currentTimeMillis();
        this.method = method;
    }

    public void printPerformanceResult(){
        long elapse = System.currentTimeMillis()-start;
        System.out.println(method+"花费"+elapse+"毫秒");
    }
}
