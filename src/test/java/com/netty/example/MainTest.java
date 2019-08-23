package com.netty.example;

import io.netty.util.concurrent.*;
import org.junit.Test;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/13
 * \* @Time: 9:10
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MainTest {
    @Test
    public void test02() {
        EventExecutor executor = new DefaultEventExecutor();

        final Promise promise = new DefaultPromise(executor);

        promise.addListener((GenericFutureListener<Future<Integer>>) future -> {
            if (future.isSuccess()) {
                System.out.println("任务结束，结果：" + future.get());
            }else {
                System.out.println("任务失败，异常：" + future.cause());
            }
        });


        promise.addListener((GenericFutureListener<Future<Integer>>) future -> System.out.println("任务结束，balabala..."));

        executor.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            // 设置 promise 的结果
             promise.setFailure(new RuntimeException());
            //promise.setSuccess(123456);
        });

        try {
            promise.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
