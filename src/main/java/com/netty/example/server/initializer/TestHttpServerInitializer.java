package com.netty.example.server.initializer;

import com.netty.example.server.handler.TestHttpServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/23
 * \* @Time: 9:15
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \ netty001
 */
public class TestHttpServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("httpServerCodec", new HttpServerCodec());
       /* pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));*/
        pipeline.addLast(new TestHttpServerHandler());
    }
}
