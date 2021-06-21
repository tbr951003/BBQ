package com.bbq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bbq.common.Constant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.bbq.common.BbqConfirmMessage;
import com.bbq.common.BrokerData;

import java.util.List;
import java.util.Map;

/**
 * by borong
 * ~
 * 2021-06-15
 **/
public class RouteFindHandler extends SimpleChannelInboundHandler<BbqConfirmMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BbqConfirmMessage msg) throws Exception {

        //刷新brokerMap
        String msgObj = msg.getMsgObj();
        Map<String , List<BrokerData>> resultMap = JSON.parseObject(msgObj ,
                new TypeReference<Map<String , List<BrokerData>>>(){});

        ConsumerManager.brokerMap.putAll(resultMap);

        //刷新brokerDataSet
        List<BrokerData> brokerDataList = ConsumerManager.brokerMap.get(Constant.TOPIC_1);
        ConsumerManager.brokerDataSet.addAll(brokerDataList);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
