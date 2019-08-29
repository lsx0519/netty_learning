package com.netty.example.jdk8.lambda;


import java.util.function.Predicate;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/28
 * \* @Time: 17:40
 * \* To change this template use File | Settings | File Templates.
 * \* Description: Predicate的使用
 * 对某种数据类型的数据进行判断
 * \
 */
public class DemoPredicator {
    public static boolean typeCheck(String s, Predicate<String> pre) {
        return pre.test(s);
    }

    /**
     * 同时满足两个条件
      */
    public static boolean typeCheck02(String s, Predicate<String> pre1, Predicate<String> pre2) {
        return pre1.and(pre2).test(s);
    }

    public static boolean typeCheck03(String s,Predicate<String> pre) {
        //boolean b = !(pre.test(s));
        return pre.negate().test(s);
    }

    public static void main(String[] args) {
        String s = "bs";

        boolean bool = typeCheck(s, (str) -> {
            return str.length() >= 0;
        });

        System.out.println(bool);

        bool = typeCheck02(s, (str) -> {
            return  s.length() >= 0;
        }, (str) -> {
            return s.contains("a");
        });

        System.out.println(bool);

        bool = typeCheck03(s, (str) -> {
            return str.length() >= 0;
        });

        System.out.println(bool);
    }
}
