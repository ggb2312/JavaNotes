package cn.lastwhisper.feature5.classloader;

/**
 * @author lastwhisper
 */
public class DemoObj {
    static {
        System.out.println("类构造器");
    }

    {
        System.out.println("对象构造器");
    }
}
