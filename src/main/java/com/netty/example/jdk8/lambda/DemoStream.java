package com.netty.example.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/29
 * \* @Time: 9:08
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class DemoStream {
    /**
     * 需求：讲一个list进行两次筛选，然后打印筛选得到的结果
     * @param list
     * 传统做法
     *
     */
    public static void test01(List<String> list) {
        List<String> lista = new ArrayList<>();
        for (String s : list) {
            if(s.startsWith("l")) {
                lista.add(s);
            }
        }

        List<String> listb = new ArrayList<>();
        for (String s : lista) {
            if (s.length() == 3) {
                listb.add(s);
            }
        }

        for (String s:listb) {
            System.out.println(s);
        }
    }


    public static void test02(List<String> list) {
        /*
        forEach的参数是一个Consumer类型的，消费型函数式接口
         */
        list.stream().filter((str) -> str.startsWith("l")).filter((s -> s.length() == 3)).forEach(s -> System.out.println(s));
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("lsx");
        list.add("zwj");
        list.add("zhr");
        list.add("cyl");

//        test01(list);

        Stream<List<String>> list1 = Stream.of(list);

       /* int[] arr = {1,2,3};
        Stream<int[]> stream = Stream.of(1,2,3);
        stream.forEach(a -> System.out.println(a));*/

//        test02(list);
        Stream<String> stringStream = Stream.of("123","1", "2", "3");
        /*stringStream.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        }).forEach(a -> System.out.println(a));*/

//        stringStream.map(s -> Integer.parseInt(s)).forEach(s -> System.out.println(s));

//        System.out.println(stringStream.count());

        // limit用于截取流的前n个元素
        //stringStream.map(s -> "hello" + s).limit(1).forEach(a -> System.out.println(a));

        stringStream.filter(a -> a.length() == 1).skip(1).forEach(a -> System.out.println(a));

        Stream<String> stringStream1 = Stream.of("lsx", "zwj");
        Stream<String> stringStream2 = Stream.of("hello", "world");
        Stream.concat(list1,Stream.concat(stringStream1,stringStream2)).forEach(s -> System.out.println(s));

    }
}
