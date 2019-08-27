package com.netty.example.nio;

import com.sun.beans.editors.ByteEditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/27
 * \* @Time: 17:38
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",8899);
        socketChannel.connect(inetSocketAddress);

        while (true) {
            try {
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    try {
                        // 连接事件
                        if (selectionKey.isConnectable()) {
                            SocketChannel clinetChannel = (SocketChannel) selectionKey.channel();

                            if (clinetChannel.isConnectionPending()) {
                                clinetChannel.finishConnect();

                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                writeBuffer.put((LocalDateTime.now() + " 连接成功").getBytes());

                                writeBuffer.flip();

                                clinetChannel.write(writeBuffer);

                                ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());

                                executorService.submit(() -> {
                                    while (true) {
                                        writeBuffer.clear();

                                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                                        String sendMessage = bufferedReader.readLine();

                                        writeBuffer.put(sendMessage.getBytes());
                                        writeBuffer.flip();
                                        clinetChannel.write(writeBuffer);
                                    }
                                });
                            }

                            clinetChannel.register(selector,SelectionKey.OP_READ);

                            selectionKeys.remove(selectionKey);
                        } else if (selectionKey.isReadable()) {
                            SocketChannel socketChannel1 = (SocketChannel) selectionKey.channel();

                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                            int count = socketChannel1.read(readBuffer);

                            if (count > 0) {
                                String message = new String(readBuffer.array(), 0, count);
                                System.out.println(message);
                            }

                            selectionKeys.remove(selectionKey);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
