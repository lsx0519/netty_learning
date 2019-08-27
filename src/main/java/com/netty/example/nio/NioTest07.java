package com.netty.example.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/27
 * \* @Time: 13:58
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 通道之间的数据传输
 *   通道之间数据传输需要有一个是FileChannel
 * \
 */
public class NioTest07 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile inputStream = new RandomAccessFile("D:\\ideaWorkspace\\netty\\src\\main\\resources\\test07.txt","rw");
        RandomAccessFile outputStream = new RandomAccessFile("D:\\ideaWorkspace\\netty\\src\\main\\resources\\output07.txt", "rw");

        FileChannel fromChannel = inputStream.getChannel();
        FileChannel toChannel = outputStream.getChannel();

        long pos = 0;
        long size = fromChannel.size();

        fromChannel.transferTo(pos, size, toChannel);

        // 等价方式
        //toChannel.transferFrom(fromChannel, pos, size);

    }
}
