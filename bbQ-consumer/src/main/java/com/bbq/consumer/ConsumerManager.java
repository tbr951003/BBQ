package com.bbq.consumer;

import com.bbq.common.BbqSendMessage;
import com.bbq.common.BrokerData;
import com.bbq.common.Constant;
import com.bbq.common.eum.ConnectionTypeEnum;
import com.bbq.remoting.RemotingClient;
import com.google.common.collect.Lists;
import io.netty.channel.ChannelFuture;

import java.util.*;

/**
 * by borong
 * ~
 * 2021-06-15
 **/
public class ConsumerManager {

    public static final Map<String , List<BrokerData>> brokerMap = new HashMap<>();


    public static final Set<BrokerData> brokerDataSet = new HashSet<>();


    public static void waitRefresh(){
        while (ConsumerManager.brokerMap.isEmpty()){
            System.out.println("wait brokerMap refresh");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("brokerMap refreshed");
    }

    public static void consumerStart(){
        List<ChannelFuture> channelList = Lists.newArrayList();
        for (BrokerData brokerData : ConsumerManager.brokerDataSet){
            ChannelFuture channelFuture = RemotingClient.connect(MsgReceived.class
                    , brokerData.getIp(), brokerData.getPort());
            channelList.add(channelFuture);
        }
        BbqSendMessage sendMessage = new BbqSendMessage();
        sendMessage.setConnectionType(ConnectionTypeEnum.CONSUMER.getCode());
        sendMessage.setTopic(Constant.TOPIC_1);
        //轮询所有broker 拉取消息
        while (true){
            for (ChannelFuture future : channelList){
                future.channel().writeAndFlush(sendMessage);
            }
        }
    }

}
