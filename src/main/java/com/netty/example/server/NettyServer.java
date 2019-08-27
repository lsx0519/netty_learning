package com.netty.example.server;

import com.netty.example.server.handler.EchoServerHandler;
import com.netty.example.server.initializer.MyChatServerInitializer;
import com.netty.example.server.initializer.MyWebSocketServerInitializer;
import com.netty.example.server.initializer.TestHttpServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 1 http服务器
 * 2 长连接
 * 3 底层传输工具
 */

public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            serverBootstrap.group(boos,worker)
                    // NioServerSocketChannel 和 NioSocketChannel 与之对应TCP
                    // NioDatagramChannel UDP
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    // 是给服务端收到新的请求的时候处理用的
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 是给新创建的连接用的  处理新创建的 SocketChannel
                    .childHandler(new MyWebSocketServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boos.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    class MyInitializer extends ChannelInitializer<NioSocketChannel> {

        @Override
        protected void initChannel(NioSocketChannel ch) throws Exception {
            ChannelPipeline channelPipeline = ch.pipeline();
            channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));
            channelPipeline.addLast(new EchoServerHandler());
        }
    }
}

