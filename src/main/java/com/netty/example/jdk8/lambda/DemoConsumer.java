package com.netty.example.jdk8.lambda;

import java.util.function.Consumer;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/27
 * \* @Time: 12:23
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class DemoConsumer {
    public static void consumString(String name , Consumer<String> consumer) {
        consumer.accept(name);
    }


    public static void main(String[] args) {
        consumString("lsx", (String name) -> {
            System.out.println("hello " + name);
        });
    }
}
