package com.netty.example.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/26
 * \* @Time: 13:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class NioTest01 {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        Channel channel = fileInputStream.getChannel();

        String string = "hello world, lsx";
        byte[] bytes = string.getBytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);



    }
}
