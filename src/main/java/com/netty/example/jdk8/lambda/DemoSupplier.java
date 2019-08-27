package com.netty.example.jdk8.lambda;

import java.util.function.Supplier;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/27
 * \* @Time: 11:35
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 *  Supplier<T> 称之为生产型接口
 * \
 */
public class DemoSupplier {
    public static String getString(Supplier<String> supplier){
        return supplier.get();
    }

    public static int getMax(Supplier<Integer> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        // 01
        String string = getString(() ->  "hhhhhh");
        System.out.println(string);

        // 02
        int[] arr = {3,5,9};


        int max1 = getMax(() -> {
            int max = Integer.MIN_VALUE;
            for(int a : arr) {
                if (a > max) {
                    max = a;
                }
            }
            return max;
        });
        System.out.println(max1);

    }
}
