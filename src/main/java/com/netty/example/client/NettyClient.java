package com.netty.example.client;

import com.netty.example.client.handler.EchoClientHandler;
import com.netty.example.client.initializer.MyChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class NettyClient {
    public static void main(String[] args) throws InterruptedException{
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new MyChatClientInitializer());


            Channel channel = bootstrap.connect("127.0.0.1", 8080).sync().channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            for ( ; ; ) {
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
