package multiThread.practice1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: XieY
 * @date: 2019/12/1
 *  解决mycontainer1提到的问题
 */
public class Mycontainer2 {
    List list =new ArrayList();

    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return  list.size();
    }

    public static void main(String[] args) {
        Mycontainer1 c=new Mycontainer1();
        Object lock = new Object();
        new Thread(()->{
            synchronized (lock){
                System.out.println("t2启动");
                if (c.size()!=5){
                    try {
                        lock.wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                System.out.println("已经到五个了");
                }
                lock.notify();
            }
        },"t2").start();

        new Thread(()->{
            synchronized (lock) {
                System.out.println("t1启动");
                for (int i = 0; i < 10; i++) {
                    c.add(i);
                    System.out.println("add" + i);
                    if (c.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        },"t1").start();



    }
}
/**
 * 总结：使用wait和notify来唤醒计数的程序，不使用循环
 */