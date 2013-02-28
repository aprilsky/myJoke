package basic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: caoxiao
 * @Date: 13-2-27 上午10:54
 */
public class ListTest {
    /**
     * 把一个list转换成 in
     */
    static void listToSqlIn(){
        List<String> list = new ArrayList<String>();
        list.add("003001_27101                  ");
        list.add("003002_27101                  ");
        list.add("zhangwenbo1_27101             ");
        list.add("        zhangwenbo2_27101     ");
        list.add("zhangyan01_27101              ");
        list.add("        zhangyong01_27101     ");


        StringBuilder sb = new StringBuilder("(");
        for (String str : list) {
            sb.append("'").append(str.trim()).append("'").append(",");
        }
        String s = sb.substring(0,sb.length()-1)+")";
        System.out.println(s);
    }

    public static void main(String[] args) {
        listToSqlIn();
    }
}
