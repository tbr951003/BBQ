package com.bbq.namesrv.processer;

import com.bbq.common.eum.RouteMsgTypeEnum;
import com.bbq.namesrv.NameSrvManager;
import io.netty.channel.ChannelHandlerContext;
import com.bbq.common.BbqRouteMessage;
import com.bbq.common.BrokerData;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class RouteRegisterProcesser implements RouteProcesser{


    @Override
    public Boolean adapter(String type) {
        return RouteMsgTypeEnum.ROUTE_REGISTER.getCode().equals(type);
    }

    /**
     * 路由注册
     */
    @Override
    public void process(ChannelHandlerContext ctx , BbqRouteMessage routeMessage) {

        String brokerName = routeMessage.getBrokerName();

        BrokerData brokerData = new BrokerData();

        brokerData.setIp(routeMessage.getIp());

        brokerData.setPort(routeMessage.getPort());

        NameSrvManager.brokerInfo.put(brokerName , brokerData);

        System.out.println("【register broker】 " + routeMessage.toString());

    }
}
