package com.netty.example.jdk8.lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/27
 * \* @Time: 9:55
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 日志案例
 * lambda的特点: 延迟加载
 *
 * \
 */
public class DemoLogger {
    public static void showLog(int level, String msg) {
        if (level == 1) {
            System.out.println(msg);
        }
    }

    public static void showLog02(int level, MessgaeBuilder messgaeBuilder) {
        if (level == 1) {
            messgaeBuilder.log();
        }
    }

    public static void startThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static Comparator<String> getComparator01() {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        };
    }

    public static Comparator<String> getComparator02() {
        return (o1, o2) ->  o1.length()-o2.length();
    }

    public static void main(String[] args) {
        String msg1 = "msg1";
        String msg2 = "msg2";
        String msg3 = "msg3";

        // 01 这里存在一个问题，若输出等级不是1，则不会打印日志。那么传递的参数，字符串拼接是多余的操作，存在性能浪费
        showLog(1,msg1 + msg2 + msg3);

        /* 02
        * lambda的特点: 延迟加载
        * 使用Lambda表达式作为参数传递，只有当日至等级为1时，才会加载log方法。若条件不满足，则不会字符串的拼接
        * */
        showLog02(2, () -> {
            return msg1 + msg2 + msg3;
        });


        // 03
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程启动了");
            }
        });

        //04 Lambda作为参数
        startThread(() -> System.out.println(Thread.currentThread().getName() + "线程启动了"));

        // 05 Lambda作为返回值
        String[] arr = {"aa","", "bbbb"};

        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr,getComparator02());

        System.out.println(Arrays.toString(arr));
    }
}
