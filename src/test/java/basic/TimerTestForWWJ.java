package basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: caoxiao
 * @Date: 13-2-27 上午10:04
 */
public class TimerTestForWWJ {
    public static void main(String[] args) {
        testSchedule();
    }
    private static void testScheduleAtFixedRate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            //从datetime开始,每隔 period（毫秒） 执行一次
            //比如每隔1秒  period = 1*1000;每隔24小时：period = 24*3600*1000
            Date datetime = sdf .parse("2013/02/27 10:14:00");
            long period = 1*1000;
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("执行了"+new Date());
                }
            },datetime,period);
        } catch (ParseException e) {
            e.printStackTrace();  //print Execption
        }

    }

    private static void testSchedule() {
        Timer timer = new Timer();
        System.out.println("-----------定时间器已经启动。。。。。。"+new Date());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("运行我了哦！！！"+new Date());
            }
        }, 1000*10, 5*1000);
        //System.out.println("cancel");
        //timer.cancel();
    }
}
