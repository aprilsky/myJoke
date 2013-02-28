package basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: caoxiao
 * @Date: 13-2-27 下午5:57
 */
public class TimeTest {
    public static void main(String[] args) {
        int syncHour = 1;
        String uploadDate = "2013-02-28 15:08:50";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date theDate =sdf.parse(uploadDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(theDate);
            calendar.add(Calendar.HOUR,syncHour);
            System.out.println(syncHour+"小时后的上传时间："+sdf.format(calendar.getTime()));
            System.out.println(calendar.getTime().after(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();  //print Execption
        }
    }
}
