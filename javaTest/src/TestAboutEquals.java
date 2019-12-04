import java.util.Objects;
/**
 * @author: XieY
 * @date: 2019/10
 */
public class TestAboutEquals {
    public static void main(String[] args) {
        //测试目的：String.equals和Objects.equals的区别
        String a = null;
        String b = "qwer";
        System.out.println(Objects.equals(a,b));  //测试结果：false
        System.out.println(a.equals(b));  //测试结果：Exception in thread "main" java.lang.NullPointerException

        //测试结论：使用前一种方法不会有报错，而后一种方法会在调用equals方法的String为null是报空指针

    }


}
