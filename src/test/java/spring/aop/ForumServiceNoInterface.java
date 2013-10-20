package spring.aop;

/**
 * @Author: caoxiao
 * @Date: 13-5-19 下午5:53
 */
public class ForumServiceNoInterface {
    public void remove(int forumId){
        //PerformanceHander.start("remove");
        System.out.println("模拟删除forum记录NO_interface：forumId"+forumId);
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();  //print Execption
        }
        //PerformanceHander.end("remove");
    }
    public void removeTopic(int topId){
        //PerformanceHander.start("removeTopic");
        System.out.println("模拟删除topic记录：topId"+topId);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();  //print Execption
        }
        //PerformanceHander.start("removeTopic");
    }
}
