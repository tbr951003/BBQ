package com.bbq.remoting;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.net.InetSocketAddress;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class RemotingClient {

    public static ChannelFuture connect(Class handlerClz, String hostName , int port) {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(hostName, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        // 添加自定义的编码器和解码器
                        // 添加POJO对象解码器 禁止缓存类加载器
                        pipeline.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                        // 设置发送消息编码器
                        pipeline.addLast(new ObjectEncoder());
                        pipeline.addLast((ChannelInboundHandler) handlerClz.newInstance());
                    }
                });

        try {
            ChannelFuture future = bootstrap.connect().sync();

            return future;

        } catch (Exception e) {
            e.printStackTrace();
            workerGroup.shutdownGracefully();
        }

        return null;
    }

}
