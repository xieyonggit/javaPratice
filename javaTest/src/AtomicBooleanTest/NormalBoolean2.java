package AtomicBooleanTest;


/**
 * @author zhuzhisheng
 * @Description
 * @date on 2016/5/26.
 */
public class NormalBoolean2 implements Runnable{


    public static boolean exits = false;

    private String name;

    public NormalBoolean2(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        if(!exits){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            exits = true;
            System.out.println(name + ",step 1");

            System.out.println(name + ",step 2");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ",step 3");
            exits = false;
        } else {
            System.out.println(name + ",step else");
        }

    }
}

