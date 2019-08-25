package com.netty.example.client.initializer;

import com.netty.example.client.handler.EchoClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.channels.Channel;

public class EchoInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel channel) throws Exception {
        ChannelPipeline channelPipeline = channel.pipeline();
        channelPipeline.addLast(new LoggingHandler()) ;
        channelPipeline.addLast(new EchoClientHandler());
    }
}
