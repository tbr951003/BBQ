package com.bbq.namesrv.processer;

import io.netty.channel.ChannelHandlerContext;
import com.bbq.common.BbqRouteMessage;

public interface RouteProcesser {

    Boolean adapter(String type);

    void process(ChannelHandlerContext ctx ,  BbqRouteMessage routeMessage);

}
