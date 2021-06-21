package com.bbq.broker;

import io.netty.channel.ChannelHandlerContext;
import com.bbq.common.BbqSendMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class BrokerManager {

    /**
     * topic和生产者发布的消息
     */
    public final static HashMap<String, Queue<BbqSendMessage>> queueMap = new HashMap<>();

    /**
     * topic和订阅的消费者
     */
    public final static HashMap<String, List<ChannelHandlerContext>> consumerMap = new HashMap<>();








}
