package com.bbq.namesrv.processer;

import com.alibaba.fastjson.JSON;
import com.bbq.common.eum.RouteMsgTypeEnum;
import com.bbq.namesrv.NameSrvManager;
import com.google.common.collect.Lists;
import io.netty.channel.ChannelHandlerContext;
import com.bbq.common.BbqConfirmMessage;
import com.bbq.common.BbqRouteMessage;
import com.bbq.common.BrokerData;

import java.util.*;

/**
 * by borong
 * ~
 * 2021-06-15
 **/
public class ConsumerRouteFindProcesser implements RouteProcesser {
    @Override
    public Boolean adapter(String type) {
        return RouteMsgTypeEnum.CONSUMER_ROUTE_FIND.getCode().equals(type);
    }

    /**
     * 消费者服务发现
     */
    @Override
    public void process(ChannelHandlerContext ctx, BbqRouteMessage routeMessage) {
        String topicListStr = routeMessage.getTopicList();
        List<String> topicList = JSON.parseArray(topicListStr , String.class);

        Map<String , List<BrokerData>> resultMap = new HashMap<>();

        for (String topic : topicList){
            Set<String> brokerNameSet = NameSrvManager.topicInfo.get(topic);
            List<BrokerData> brokerDataList = Lists.newArrayList();
            for (String brokerName : brokerNameSet){
                BrokerData brokerData = NameSrvManager.brokerInfo.get(brokerName);
                brokerDataList.add(brokerData);
            }
            resultMap.put(topic , brokerDataList);
        }

        if (!resultMap.isEmpty()){
            BbqConfirmMessage confirmMessage = new BbqConfirmMessage();
            confirmMessage.setResult("ok!");
            confirmMessage.setMsgObj(JSON.toJSONString(resultMap));

            ctx.writeAndFlush(confirmMessage);

            System.out.println("【consumer】 route find succ!");
        }else {
            System.out.println("brokerInfo not found");
        }

    }
}
