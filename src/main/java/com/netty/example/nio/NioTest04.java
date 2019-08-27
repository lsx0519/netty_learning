package com.netty.example.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/26
 * \* @Time: 14:33
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class NioTest04 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("D:\\ideaWorkspace\\netty\\src\\main\\resources\\test04.txt");
        FileOutputStream outputStream = new FileOutputStream("D:\\ideaWorkspace\\netty\\src\\main\\resources\\output04.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        // 零拷贝机制
        ByteBuffer buffer = ByteBuffer.allocateDirect(512);

        while (true) {
            buffer.clear();
            System.out.println("position:" + buffer.position() + ",limit:" + buffer.limit());
            int read = inputChannel.read(buffer);

            if (read == -1) {
                break;
            }
            System.out.println("position:" + buffer.position() + ",limit:" + buffer.limit());
            buffer.flip();
            System.out.println("position:" + buffer.position() + ",limit:" + buffer.limit());

            outputChannel.write(buffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
