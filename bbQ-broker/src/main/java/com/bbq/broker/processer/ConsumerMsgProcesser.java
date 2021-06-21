package com.bbq.broker.processer;

import com.bbq.broker.BrokerManager;
import com.bbq.common.eum.BrokerMsgTypeEnum;
import io.netty.channel.ChannelHandlerContext;
import com.bbq.common.BbqSendMessage;

import java.util.Queue;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class ConsumerMsgProcesser implements MsgProcesser {


    @Override
    public Boolean adapter(String type) {
        return BrokerMsgTypeEnum.CONSUMER_PULL.getCode().equals(type);
    }

    @Override
    public void handle(ChannelHandlerContext ctx , BbqSendMessage bbqSendMessage) {

        String topic = bbqSendMessage.getTopic();
        Queue<BbqSendMessage> bbqSendMessages = BrokerManager.queueMap.get(topic);

        if (!bbqSendMessages.isEmpty()){
            BbqSendMessage message = bbqSendMessages.poll();

            ctx.writeAndFlush(message);
        }


    }
}
