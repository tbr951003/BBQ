package com.bbq.broker;

import com.bbq.broker.processer.MsgProcesser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.reflections.Reflections;
import com.bbq.common.BbqSendMessage;

import java.util.Set;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class Dispatcher extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BbqSendMessage bbqSendMessage = (BbqSendMessage) msg;

        String connectionType = bbqSendMessage.getConnectionType();

        MsgProcesser msgProcesser = getProcesser(connectionType);

        if (msgProcesser != null){
            msgProcesser.handle(ctx , bbqSendMessage);
        }

    }

    private MsgProcesser getProcesser(String connectionType) throws Exception {

        Reflections reflections = new Reflections("com.bbq");
        Set<Class<? extends MsgProcesser>> processers = reflections.getSubTypesOf(MsgProcesser.class);
        for (Class clz : processers) {
            MsgProcesser processer = (MsgProcesser) clz.newInstance();
            if (processer.adapter(connectionType)) {
                return processer;
            }
        }
        return null;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();

    }
}
