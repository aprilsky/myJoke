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
        int syncHour = 24;
        String uploadDate = "2013-03-01 16:04:43";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date theDate =sdf.parse(uploadDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(theDate);
            calendar.add(Calendar.HOUR,syncHour);
            System.out.println("上传时间后的"+syncHour+"小时："+sdf.format(calendar.getTime()));
            System.out.println(new Date().after(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();  //print Execption
        }
    }
}
