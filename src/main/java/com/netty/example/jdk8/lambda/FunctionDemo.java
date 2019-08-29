package com.netty.example.jdk8.lambda;

import java.util.function.Function;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/29
 * \* @Time: 8:44
 * \* To change this template use File | Settings | File Templates.
 * \* Description:   Function<T, R>  根据一个类型的数据得到另一个类的数据类型
 *  T为前置条件，R为后置条件
 *
 * \
 */
public class FunctionDemo {
    /**
     *
     * @param str
     * @param function
     * @return Integer
     * apply的参数是 Function变量的前置条件变量，返回值为后置条件变量
     */
    public static Integer test01(String str, Function<String,Integer> function) {
        return function.apply(str);
    }

    /**
     * 内置函数andThen的使用
     *第一个Function变量的后置条件是第二个Function变量的前置条件
      */
    public static String test02(String str, Function<String,Integer> function1,Function<Integer,String> function2) {
        return function1.andThen(function2).apply(str);
    }

    public static Integer test03(String str, Function<String,String> function1,Function<String,Integer> function2) {
        return function1.andThen(function2).apply(str);
    }




    public static void main(String[] args) {
        String s = "123";
        System.out.println(test01(s, (a) -> Integer.parseInt(a)));

        String s1 = test02(s, (str) -> Integer.parseInt(str) + 10, (a) -> Integer.toString(a));
        System.out.println(s1);

        Integer integer = test03("lsx,29" , (str) -> str.split(",")[1], (str) -> Integer.parseInt(str) + 100);
        System.out.println(integer);
    }
}
