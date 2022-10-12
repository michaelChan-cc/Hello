package jvm;

/**
 * 栈溢出： 无限递归问题
 *
 */
public class StackOverFlow {

    private int s_len = 1;

    private void incre(){
        s_len++;
        incre();
    }

    // 参数变多， 局部变量变大了。 单个栈帧就变大了
    private void incre1(int a, String b){
        s_len++;
        incre1(a, b);
    }

    // -Xss256K
    public static void main(String[] args) {
        StackOverFlow stack = new StackOverFlow();
        try {
            stack.incre();
//            stack.incre1(1, "cc");
        } catch (Throwable e) {
            System.out.println("stack.len: " + stack.s_len);
            /**
             *  1M   -  stack.len: 11417
             *  512k -  stack.len: 5461
             *  108k -  stack.len: 993
             */
            e.printStackTrace();
        }
    }

}
