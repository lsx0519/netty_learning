package com.netty.example.server.initializer;

import com.netty.example.server.handler.EchoServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/23
 * \* @Time: 16:21
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MyInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new LoggingHandler(LogLevel.INFO));
        channelPipeline.addLast(new EchoServerHandler());
    }
}
