package com.bbq.namesrv.processer;

import com.alibaba.fastjson.JSON;
import com.bbq.common.eum.RouteMsgTypeEnum;
import com.bbq.namesrv.NameSrvManager;
import io.netty.channel.ChannelHandlerContext;
import com.bbq.common.BbqConfirmMessage;
import com.bbq.common.BbqRouteMessage;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class ProducerRouteFindProcesser implements RouteProcesser{
    @Override
    public Boolean adapter(String type) {
        return RouteMsgTypeEnum.PRODUCER_ROUTE_FIND.getCode().equals(type);
    }

    /**
     * 生产者服务发现 返回所有broker信息
     */
    @Override
    public void process(ChannelHandlerContext ctx, BbqRouteMessage routeMessage) {

        BbqConfirmMessage confirmMessage = new BbqConfirmMessage();
        confirmMessage.setResult("ok!");
        confirmMessage.setMsgObj(JSON.toJSONString(NameSrvManager.brokerInfo));

        ctx.writeAndFlush(confirmMessage);

        System.out.println("【producer】 route find succ!");
    }
}
