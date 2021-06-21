package com.bbq.namesrv.processer;

import com.alibaba.fastjson.JSON;
import com.bbq.common.eum.RouteMsgTypeEnum;
import com.bbq.namesrv.NameSrvManager;
import io.netty.channel.ChannelHandlerContext;
import com.bbq.common.BbqRouteMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * by borong
 * ~
 * 2021-06-15
 **/
public class HeartBeatProcesser implements RouteProcesser{
    @Override
    public Boolean adapter(String type) {
        return RouteMsgTypeEnum.HEART_BEAT.getCode().equals(type);
    }

    /**
     * broker心跳
     * 并更新topic信息
     */
    @Override
    public void process(ChannelHandlerContext ctx, BbqRouteMessage routeMessage) {
        String brokerName = routeMessage.getBrokerName();
        String topicStr = routeMessage.getTopicList();
        //转list
        List<String> topicList = JSON.parseArray(topicStr , String.class);
        if (!topicList.isEmpty()){
            for (String topic : topicList){
                //有则追加,没有则新增
                if (NameSrvManager.topicInfo.containsKey(topic)){
                    NameSrvManager.topicInfo.get(topic).add(brokerName);
                }else{
                    Set<String> set = new HashSet<>();
                    set.add(brokerName);
                    NameSrvManager.topicInfo.put(topic , set);
                }
            }
            System.out.println("【HeartBeat】 update topicInfo");
        }else{
            System.out.println("【HeartBeat】 topicInfo is empty");
        }

    }
}
