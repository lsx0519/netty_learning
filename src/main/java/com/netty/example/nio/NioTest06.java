package com.netty.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/27
 * \* @Time: 9:14
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 关于Buffer的scattering和gathering
 * \
 */
public class NioTest06 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLen = 2 + 3 + 4;

        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int bytesRead = 0;

            while (bytesRead < messageLen) {

                int r = (int) socketChannel.read(buffers);
                bytesRead = +r;
                System.out.println("bytesRead" + bytesRead);

                Arrays.asList(buffers).stream().map(buffer -> "position: " + buffer.position() + ",limit: " + buffer.limit()).forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(buffer -> buffer.flip());

            long bytesWritten = 0;
            while (bytesWritten < messageLen) {
                long r = socketChannel.write(buffers);
                bytesWritten += r;
            }

            Arrays.asList(buffers).forEach(buffer -> buffer.clear());

            System.out.println("bytesRead=" + bytesRead + "bytesWritten=" + bytesWritten + "messageLen=" + messageLen);
        }

    }
}
