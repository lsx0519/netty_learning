package com.netty.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;


public class NettyClient {
    public static void main(String[] args) throws InterruptedException{
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            ChannelPipeline channelPipeline = channel.pipeline();
                            channelPipeline.addLast(new LoggingHandler()) ;
                            channelPipeline.addLast(new EchoClientHandler());
                        }
                    });


            ChannelFuture channel = bootstrap.connect("127.0.0.1", 8080).sync();

            channel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
