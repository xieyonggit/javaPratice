package multiThread.practice1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: XieY
 * @date: 2019/12/1
 * 实现一个容器，提供两个方法，add, size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数。当个数到5个时，钱程2给出提示并结束
 */
public class Mycontainer1 {
    volatile List list =new ArrayList();

    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return  list.size();
    }

    public static void main(String[] args) {
        Mycontainer1 c=new Mycontainer1();
        new Thread(()->{
            for(int i=0;i<10;i++){
                c.add(i);
                System.out.println("add"+i);

                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"t1").start();


        new Thread(()->{
            while(true){
                if (c.size()==5){
                    System.out.println("已经到五个了");
                    break;
                }
            }
        },"t2").start();
    }
}

/**
 * 总结：
 * 使用了volatile，保证了第二个线程能获取到第一个线程中变化的值
 * 问题、改进点：第二个线程的循坏太浪费资源
 */
