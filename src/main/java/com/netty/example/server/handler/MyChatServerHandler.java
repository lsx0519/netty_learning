package com.netty.example.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

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
    // 存储客户端已建立连接的channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(ch.remoteAddress() + " 发送消息：" + msg + "\n");
            }else {
                ch.writeAndFlush("自己的消息：" + msg + "\n");
            }
        });
    }

    /**
     * 客户端有新连接建立好了
     * */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();


        // 通知所有已建立的连接，有新的客户端连接
        channelGroup.writeAndFlush("服务器：" + channel.remoteAddress() + "加入" + "\n");

        channelGroup.add(channel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        System.out.println(channel.remoteAddress() + "上线了" + "\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        System.out.println(channel.remoteAddress() + "下线了" + "\n");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("服务器：" + channel.remoteAddress() + "退出" + "\n");

        System.out.println(channelGroup.size());
//        channelGroup.remove(channel);  // 会自动调用
    }
}
