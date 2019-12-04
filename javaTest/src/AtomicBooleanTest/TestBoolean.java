package AtomicBooleanTest;

/**
 * @author zhuzhisheng
 * @Description
 * @date on 2016/5/26.
 */
public class TestBoolean {
    public static void main(String[] args) {

//        Thread thread1 = new Thread(new NormalBoolean2("李四"));
//        Thread thread2 = new Thread(new NormalBoolean2("张三"));
//
//        thread1.start();
//        thread2.start();
        //测试结果，两个线程交替运行和输出
        //-------------------------------------------------------

        Thread thread3 = new Thread(new TestAtomicBoolean("李四"));
        Thread thread4 = new Thread(new TestAtomicBoolean("张三"));

        thread3.start();
        thread4.start();
    }
}

