import java.util.Objects;

/**
 * @author: XieY
 * @date: 2019/10/30
 */
public class TestAboutByteCompute {
    public static void main(String[] args) {
        //测试目的：位运算的特点
        System.out.println(1<<31); //测试结果：-2147483648
        System.out.println(1<<30); //测试结果：1073741824
        System.out.println(1<<2); //测试结果：4

        //测试结论：
        // 位运算的位数上线为32位，超出便会循环
        // 位运算就是二进制的移动


    }

}
