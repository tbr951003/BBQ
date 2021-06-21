package com.bbq.consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.bbq.common.BbqSendMessage;

/**
 * by borong
 * ~
 * 2021-06-15
 **/
public class MsgReceived extends SimpleChannelInboundHandler<BbqSendMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BbqSendMessage msg) throws Exception {
        System.out.println("consumer recived :" + msg.toString());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
