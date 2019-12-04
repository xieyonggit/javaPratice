package AtomicBooleanTest;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhuzhisheng
 * @Description
 * @date on 2016/5/26.
 */
public class TestAtomicBoolean implements Runnable{

    public static AtomicBoolean exits = new AtomicBoolean(false);

    private String name;

    public TestAtomicBoolean(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        if(exits.compareAndSet(false,true)){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ",step 1");

            System.out.println(name + ",step 2");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ",step 3");
            exits.set(false);
        } else {
            System.out.println(name + ",step else");
        }

    }
}
