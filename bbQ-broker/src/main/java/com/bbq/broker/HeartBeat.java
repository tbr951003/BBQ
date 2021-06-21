package com.bbq.broker;

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
public class HeartBeat implements Runnable{

//    private static ChannelFuture channelFuture;
//
//    public HeartBeat(String host , int port){
//        ChannelFuture future2NameSrv = RemotingClient.connect(Register.class, host, port);
//        channelFuture = future2NameSrv;
//    }

    /**
     * 定时向nameServer发送心跳及新的topic信息
     */
    @Override
    public void run() {
        try {

            ChannelFuture channelFuture = RemotingClient.connect(Register.class
                    , Constant.NAME_SERVER_HOST, Constant.NAME_SERVER_PORT);

            while (true) {

                BbqRouteMessage routeMessage = new BbqRouteMessage();
                routeMessage.setBrokerName(Constant.BROKER_NAME_1);
                routeMessage.setRouteMsgType(RouteMsgTypeEnum.HEART_BEAT.getCode());

                List<String> topicList = Lists.newArrayList();
                if (!BrokerManager.queueMap.isEmpty()){
                    for (String key : BrokerManager.queueMap.keySet()){
                        topicList.add(key);
                    }
                }
                routeMessage.setTopicList(JSON.toJSONString(topicList));

                channelFuture.channel().writeAndFlush(routeMessage);

                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
