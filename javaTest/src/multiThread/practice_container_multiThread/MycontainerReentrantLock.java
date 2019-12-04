package multiThread.practice_container_multiThread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/***/
/**
 * @author: XieY
 * @date: 2019/12/4
 * 使用ReentrantLock和Condition来简便的解决题目
 * 这里我写的错误的，不正确的类
 */
public class MycontainerReentrantLock<T> {
    private int count=0;
    private LinkedList<T> list =new LinkedList();
    final  private int MAX=10;
    private ReentrantLock lock = new ReentrantLock();/**改正：防止外面类来访问，因此设为private*/
    private Condition consumer = lock.newCondition();
    private Condition producer = lock.newCondition();

    void put(T t){
        lock.lock();
        while(list.size()==MAX) {
            try {
                producer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            list.add(t);
            ++count;
            consumer.signalAll();/**这三行不能放到这里，应该放到lock.unlock()前面，锁如果都解开了，后面这些代码还怎么弄*/

        }
    }

    T get(){
        T t =null;
        lock.lock();
        while(list.size()==0){
            try{
//                lock.lock();
                consumer.await();
                count--;
                producer.signalAll();/**这两行也不能放到这里，这些代码怎么能循环呢*/

            }catch(InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        return list.removeFirst();
//        count--;
//        producer.signalAll();
//        return list.removeFirst();
    }


    public static void main(String[] args) {
        MycontainerReentrantLock<String> c=new MycontainerReentrantLock<>();

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
