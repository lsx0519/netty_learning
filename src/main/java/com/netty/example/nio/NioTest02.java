package com.netty.example.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channel;
import java.security.SecureRandom;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/26
 * \* @Time: 13:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class NioTest02 {
    public static void main(String[] args) throws FileNotFoundException {
        IntBuffer buffer = IntBuffer.allocate(10);

        System.out.println("capacity" + buffer.capacity());
        System.out.println("limit" + buffer.limit());

        for (int i = 0; i < 5; i++) {
            int rand = new SecureRandom().nextInt(20);
            buffer.put(rand);
        }

        System.out.println("capacity" + buffer.capacity());
        System.out.println("limit" + buffer.limit());

        buffer.flip();

        System.out.println("capacity" + buffer.capacity());
        System.out.println("limit" + buffer.limit());

        while (buffer.hasRemaining()) {
            System.out.println("position" + buffer.position());
            System.out.println("capacity" + buffer.capacity());
            System.out.println("limit" + buffer.limit());
            System.out.println(buffer.get());
        }



    }



}
