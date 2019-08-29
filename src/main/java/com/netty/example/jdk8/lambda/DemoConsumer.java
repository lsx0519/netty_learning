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

    public static void consumString02(String name, Consumer<String> consumer1, Consumer<String> consumer2){
        consumer1.andThen(consumer2).accept(name);
    }

    public static void consumString03(String[] arr, Consumer<String> consumer1, Consumer<String> consumer2){
        for (int i = 0; i < arr.length; i++) {
            consumer1.andThen(consumer2).accept(arr[i]);
        }

    }


    public static void main(String[] args) {
        consumString("lsx", (String name) -> {
            System.out.println("hello " + name);
        });

        consumString02("Didi", (str) -> {
            System.out.println(str.toUpperCase());
        },(str) -> {
            System.out.println(str.toLowerCase());
        });

        String[] arr = {"lsx:male","zly:female"};

        consumString03(arr,(str)->{
            System.out.print("name:" + str.split(":")[0] + ", ");
        },(str) -> {
            System.out.println("sex:" + str.split(":")[1]);
        });
    }
}
