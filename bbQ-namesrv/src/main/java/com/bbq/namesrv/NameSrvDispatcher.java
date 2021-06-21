package com.bbq.namesrv;

import com.bbq.namesrv.processer.RouteProcesser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.reflections.Reflections;
import com.bbq.common.BbqRouteMessage;

import java.util.Set;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class NameSrvDispatcher extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BbqRouteMessage routeMessage = (BbqRouteMessage) msg;

        String routeMsgType = routeMessage.getRouteMsgType();

        RouteProcesser routeProcessor = getRouteProcessor(routeMsgType);

        routeProcessor.process(ctx , routeMessage);

    }

    public RouteProcesser getRouteProcessor(String routeMsgType) throws Exception {
        Reflections reflections = new Reflections("com.bbq");
        Set<Class<? extends RouteProcesser>> processors = reflections.getSubTypesOf(RouteProcesser.class);
        for (Class clz : processors){
            RouteProcesser processer = (RouteProcesser) clz.newInstance();
            if (processer.adapter(routeMsgType)){
                return processer;
            }
        }
        return null;
    }



}
