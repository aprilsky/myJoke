package basic;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author: caoxiao
 * @Date: 13-3-15 下午10:20
 */
public class MyClassLoader extends ClassLoader{

    private String name = "";

    private String path = "";

    private String fileType = ".class";

    /**
     * Creates a new class loader using the specified parent class loader for
     * delegation.
     * <p/>
     * <p> If there is a security manager, its {@link
     * SecurityManager#checkCreateClassLoader()
     * <tt>checkCreateClassLoader</tt>} method is invoked.  This may result in
     * a security exception.  </p>
     *
     * @param parent The parent class loader
     * @throws SecurityException If a security manager exists and its
     *                                     <tt>checkCreateClassLoader</tt> method doesn't allow creation
     *                                     of a new class loader.
     * @since 1.2
     */
    public MyClassLoader(ClassLoader parent, String name) {
        super(parent);
        this.name = name;
    }
    public MyClassLoader(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Finds the class with the specified <a href="#name">binary name</a>.
     * This method should be overridden by class loader implementations that
     * follow the delegation model for loading classes, and will be invoked by
     * the {@link #loadClass <tt>loadClass</tt>} method after checking the
     * parent class loader for the requested class.  The default implementation
     * throws a <tt>ClassNotFoundException</tt>.  </p>
     *
     * @param name  类文件的名字
     * @return The resulting <tt>Class</tt> object
     * @throws ClassNotFoundException If the class could not be found
     * @since 1.2
     *defineClass()将一个 byte 数组转换为 Class 类的实例。必须分析 Class，然后才能使用它
     * 参数：
     * name - 所需要的类的二进制名称，如果不知道此名称，则该参数为 null
     * b - 组成类数据的字节。off 与 off+len-1 之间的字节应该具有《Java Virtual Machine Specification》定义的有效类文件的格式。
     * off - 类数据的 b 中的起始偏移量
     * len - 类数据的长度
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);//获得类文件的字节数组
        return this.defineClass(name, data, 0, data.length);//

    }
    /**
     *
     * @param name 类文件的名字
     * @return 类文件的 字节数组
     * 通过类文件的名字获得类文件的字节数组，其实主要就是用
     * 输入输出流实现。
     */
    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try {
            this.name = this.name.replaceAll("\\.","/");
            is = new FileInputStream(new File(path + name.replaceAll("\\.","/") + fileType));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }

    public static void main(String[] args) throws Exception {
//创建一个loader1类加载器，设置他的加载路径为d:\\serverlib\\，设置默认父加载器为系统类加载器
        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath("/Users/apple/Desktop/");
//创建一个loader2类加载器，设置他的加载路径为d:\\clientlib\\，并设置父加载器为loader1
       /* MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
        loader2.setPath("d:\\myapp\\clientlib\\");
//创建一个loader3类加载器，设置他的加载路径为d:\\otherlib\\，并设置父加载器为根类加载器
        MyClassLoader loader3 = new MyClassLoader(null, "loader3");
        loader3.setPath("d:\\myapp\\otherlib\\");
        test(loader2);
        System.out.println("----------");
        test(loader3);*/
        test(loader1);
    }
    public static void test(ClassLoader loader) throws Exception {
        Class clazz = loader.loadClass("Hello");
        Object object = clazz.newInstance();
        System.out.println(object);
    }
}
