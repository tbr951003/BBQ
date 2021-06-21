package com.bbq.broker.processer;

import io.netty.channel.ChannelHandlerContext;
import com.bbq.common.BbqSendMessage;

public interface MsgProcesser {

    Boolean adapter(String type);

    void handle(ChannelHandlerContext ctx , BbqSendMessage bbqSendMessage);

}
