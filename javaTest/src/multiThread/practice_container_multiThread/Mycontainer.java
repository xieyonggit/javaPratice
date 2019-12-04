package multiThread.practice_container_multiThread;

import com.sun.org.apache.xerces.internal.parsers.CachingParserPool;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author: XieY
 * @date: 2019/12/3
 * 有一个容器，大小为10，使用多线程是这个容器可以放东西，可以拿东西
 * 使用wait和notify
 */
public class Mycontainer<T>{
    private int count=0;
    LinkedList<T> list = new LinkedList();
    private int MAX=10;
    public synchronized void put(T t){
        while (list.size()==MAX){
           try{
               this.wait();
           }catch(InterruptedException e){
               e.printStackTrace();
           }
        }
        list.add(t);
        ++count;
        this.notifyAll();
    }

    public synchronized T get(){

        while(list.size()==0){
            try{
                this.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        count--;
        this.notifyAll();
        return list.removeFirst();


    }

    public static void main(String[] args) {
        Mycontainer<String> c=new Mycontainer();

        //启动消费者线程
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<5;j++){
                    System.out.println(c.get());
                }
            },"c"+i).start();
        }

        try{
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }


        //启动生产者线程
        for(int i=0;i<2;i++){
            new Thread(()->{
                for(int j=0;j<25;j++){
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }

    }
}
