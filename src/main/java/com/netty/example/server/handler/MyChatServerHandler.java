package com.netty.example.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: lsx
 * \* @Date: 2019/8/23
 * \* @Time: 9:23
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
//    private static

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }

    /**
     * 客户端有新连接建立好了
     * */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }
}
