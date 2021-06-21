package com.bbq.broker.processer;

import com.bbq.broker.BrokerManager;
import com.bbq.common.eum.BrokerMsgTypeEnum;
import io.netty.channel.ChannelHandlerContext;
import com.bbq.common.BbqSendMessage;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * by borong
 * ~
 * 2021-06-08
 **/

public class ProducerMsgProcesser implements MsgProcesser {


    @Override
    public Boolean adapter(String type) {
        return BrokerMsgTypeEnum.PRODUCER_SEND.getCode().equals(type);
    }

    @Override
    public void handle(ChannelHandlerContext ctx , BbqSendMessage bbqSendMessage) {
        String topic = bbqSendMessage.getTopic();

        Queue<BbqSendMessage> queue = BrokerManager.queueMap.get(topic);
        //没有则新建一个
        if (queue == null){
            queue = new LinkedBlockingDeque<>();
            BrokerManager.queueMap.put(topic , queue);
        }
        queue.offer(bbqSendMessage);

        System.out.println("broker received msg " + bbqSendMessage.toString());



    }
}
