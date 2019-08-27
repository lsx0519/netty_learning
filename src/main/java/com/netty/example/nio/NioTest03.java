package com.netty.example.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class NioTest03 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("D:\\ideaWorkspace\\netty\\src\\main\\resources\\test03.txt");
        FileOutputStream outputStream = new FileOutputStream("D:\\ideaWorkspace\\netty\\src\\main\\resources\\output.txt");

        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            buffer.clear();

            int read = inputChannel.read(buffer);

            System.out.println(read);

            if (read == -1) {
                break;
            }

            buffer.flip();

            outputChannel.write(buffer);
        }

        inputChannel.close();
        outputChannel.close();
    }
}
