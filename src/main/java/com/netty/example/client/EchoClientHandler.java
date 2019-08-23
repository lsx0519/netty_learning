package com.netty.example.client;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/12
 * \* @Time: 17:17
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        ctx.writeAndFlush(Unpooled.copiedBuffer("client short msg", StandardCharsets.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        logger.info("client received:{}", msg.toString(StandardCharsets.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        logger.error(cause.getMessage());
        ctx.close();
    }

}

