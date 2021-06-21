package com.bbq.consumer;

import com.alibaba.fastjson.JSON;
import com.bbq.common.Constant;
import com.bbq.common.eum.RouteMsgTypeEnum;
import com.bbq.remoting.RemotingClient;
import com.google.common.collect.Lists;
import io.netty.channel.ChannelFuture;
import com.bbq.common.BbqRouteMessage;

import java.util.List;

/**
 * by borong
 * ~
 * 2021-06-15
 **/
public class RouteFind implements Runnable{
    /**
     * 从nameserver拿到符合主题的broker
     */
    @Override
    public void run() {
        ChannelFuture channelFuture = RemotingClient.connect(RouteFindHandler.class
                , Constant.NAME_SERVER_HOST, Constant.NAME_SERVER_PORT);
        BbqRouteMessage routeMessage = new BbqRouteMessage();
        routeMessage.setRouteMsgType(RouteMsgTypeEnum.CONSUMER_ROUTE_FIND.getCode());
        List<String> topicList = Lists.newArrayList();
        topicList.add(Constant.TOPIC_1);
        routeMessage.setTopicList(JSON.toJSONString(topicList));
        while (true){
            channelFuture.channel().writeAndFlush(routeMessage);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
