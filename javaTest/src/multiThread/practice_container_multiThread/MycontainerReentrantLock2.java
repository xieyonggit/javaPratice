package multiThread.practice_container_multiThread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: XieY
 * @date: 2019/12/4
 *  * 使用ReentrantLock和Condition来简便的解决题目
 */
public class MycontainerReentrantLock2<T> {
    private int count=0;
    final private LinkedList<T> list =new LinkedList();
    final  private int MAX=10;
    private ReentrantLock lock = new ReentrantLock();
    private Condition consumer = lock.newCondition();
    private Condition producer = lock.newCondition();

    public void put(T t){
        try {
        lock.lock();

        while (list.size() == MAX) {
            producer.await();
        }
        list.add(t);
        ++count;
        consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public T get(){
        T t =null;
        try {
        lock.lock();

        while (list.size() == 0) {
            consumer.await();
        }
        count--;
        t=list.removeFirst();
        producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;

    }


    public static void main(String[] args) {
        MycontainerReentrantLock2<String> c=new MycontainerReentrantLock2<>();

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
