package com.bbq.broker;

import com.bbq.common.BbqRouteMessage;
import com.bbq.common.Constant;
import com.bbq.common.eum.RouteMsgTypeEnum;
import com.bbq.remoting.RemotingClient;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.bbq.common.BbqConfirmMessage;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class Register extends SimpleChannelInboundHandler<BbqConfirmMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, BbqConfirmMessage msg) throws Exception {

        System.out.println("client received:" + msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public static void doRegister(){

        ChannelFuture channelFuture = RemotingClient.connect(Register.class
                , Constant.NAME_SERVER_HOST, Constant.NAME_SERVER_PORT);

        BbqRouteMessage routeMessage = new BbqRouteMessage();
        routeMessage.setRouteMsgType(RouteMsgTypeEnum.ROUTE_REGISTER.getCode());
        routeMessage.setBrokerName(Constant.BROKER_NAME_1);
        routeMessage.setIp(Constant.BROKER_HOST_1);
        routeMessage.setPort(Constant.BROKER_PORT_1);

        channelFuture.channel().writeAndFlush(routeMessage);

    }

}
