package com.netty.example.jdk8.functionalinterface;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/27
 * \* @Time: 9:45
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Demo {
    public static void show(MyFunctionalInterface myFunctionalInterface) {
        myFunctionalInterface.method();
    }

    public static void main(String[] args) {
        show(new MyFunctionalInterface() {
            @Override
            public void method() {
                System.out.println("匿名内部类");
            }
        });

        // 使用lambda表达式
        show(() -> {
            System.out.println("使用lambda表达式");
        });

        // 使用简化lambda表达式
        show(() -> System.out.println("使用简化lambda表达式"));

    }
}
