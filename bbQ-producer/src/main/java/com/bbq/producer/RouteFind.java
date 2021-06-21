package com.bbq.producer;

import com.bbq.common.eum.RouteMsgTypeEnum;
import com.bbq.remoting.RemotingClient;
import io.netty.channel.ChannelFuture;
import com.bbq.common.BbqRouteMessage;

/**
 * by borong
 * ~
 * 2021-06-09
 **/
public class RouteFind implements Runnable {

   private static ChannelFuture channelFuture;

    public RouteFind(String host, int port) {

        ChannelFuture future2NameServer = RemotingClient.connect(RouteFindHandler.class, host, port);
        channelFuture = future2NameServer;
    }

    /**
     * 从nameserver上获取broker信息
     */
    @Override
    public void run() {
        try {
            while (true) {

                BbqRouteMessage bbqRouteMessage = new BbqRouteMessage();
                bbqRouteMessage.setRouteMsgType(RouteMsgTypeEnum.PRODUCER_ROUTE_FIND.getCode());

                RouteFind.channelFuture.channel().writeAndFlush(bbqRouteMessage);

                Thread.sleep(100000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
