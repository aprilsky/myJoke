package spring;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.*;

/**
 * @Author: caoxiao
 * @Date: 13-4-4 下午10:22
 */
public class TestResource {
    public static void main(String[] args) {
        testResource();
    }
    static void testResource(){
        Resource resource = new FileSystemResource("/Users/apple/DeskTop/test.txt");
        System.out.println(resource.exists());
        try {
            InputStream is = resource.getInputStream();
            Reader reader = new InputStreamReader(is);

            /*char[] bytes = new char[1024];
            while (reader.read(bytes,0,bytes.length-1)!=-1){
                System.out.print();
            }*/
            while (reader.read()!=-1){
                System.out.println(reader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();  //print Execption
        }
    }
}
