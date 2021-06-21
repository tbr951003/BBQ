package com.bbq.producer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.bbq.common.BbqConfirmMessage;


/**
 * @program: bbQ
 * @description:
 * @author: borong
 * @create: 2021-05-19
 **/
public class ProducerRecived extends SimpleChannelInboundHandler<BbqConfirmMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BbqConfirmMessage msg) throws Exception {

        System.out.println("producer received:" + msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
