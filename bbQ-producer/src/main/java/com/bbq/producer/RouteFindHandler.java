package com.bbq.producer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.bbq.common.BbqConfirmMessage;

/**
 * by borong
 * ~
 * 2021-06-09
 **/
public class RouteFindHandler extends SimpleChannelInboundHandler<BbqConfirmMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BbqConfirmMessage msg) throws Exception {
        if (msg != null && msg.getMsgObj() != null){
            BbqProducer.refreshBrokerInfo(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
