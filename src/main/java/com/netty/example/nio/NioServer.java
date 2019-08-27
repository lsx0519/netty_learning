package com.netty.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/27
 * \* @Time: 16:42
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);
        serverSocket.bind(inetSocketAddress);

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try{
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if(selectionKey.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                            client = serverSocketChannel1.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                            String key = "[" + UUID.randomUUID() + "]";
                            clientMap.put(key, client);
                            selectionKeys.remove(selectionKey);
                        } else if (selectionKey.isReadable()) {
                            client = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                            int count = client.read(byteBuffer);

                            if (count >0) {
                                byteBuffer.flip();

                                Charset charset = Charset.forName("UTF-8");
                                String receivedMessage = String.valueOf(charset.decode(byteBuffer).array());

                                System.out.println(client + ":" + receivedMessage);

                                String sendKey = "";

                                for (Map.Entry<String,SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        sendKey = entry.getKey();
                                    }
                                }

                                for (Map.Entry<String,SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel socketChannel = entry.getValue();

                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                                    writeBuffer.put((sendKey + ":" + receivedMessage).getBytes());

                                    writeBuffer.flip();

                                    socketChannel.write(writeBuffer);
                                }
                            }
                            selectionKeys.remove(selectionKey);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }catch (Exception e) {

            }

        }

    }
}
