package com.netty.example.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/26
 * \* @Time: 15:57
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 内存映射文件  MappedByteBuffer
 * \
 */
public class NioTest05 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\ideaWorkspace\\netty\\src\\main\\resources\\test04.txt","rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,5);


    }
}
