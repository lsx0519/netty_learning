package com.netty.example.jdk8.functionalRefer;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/29
 * \* @Time: 10:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class DemoPrint {
    public static void printString(String str, Printable printable) {
        printable.print(str.toUpperCase());
    }

    public static void main(String[] args) {

        printString("lsx", new Printable() {
            @Override
            public void print(String hello_world) {
                System.out.println(hello_world);
            }
        });

        printString("yd", s -> System.out.println(s));

        printString("hello" , System.out::println);

    }
}
