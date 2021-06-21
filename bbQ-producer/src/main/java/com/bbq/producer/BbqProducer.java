package com.bbq.producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bbq.common.*;
import com.bbq.common.eum.ConnectionTypeEnum;
import com.bbq.remoting.RemotingClient;
import io.netty.channel.ChannelFuture;

import java.util.*;

/**
 * by borong
 * ~
 * 2021-06-04
 **/
public class BbqProducer {

    private Random random = new Random();

    private final static Map<String, BrokerData> brokerInfo = new HashMap<>();

    private static ChannelFuture channelFuture;

    private static BbqProducer bbqProducer;

    public synchronized static BbqProducer init(){
        if (bbqProducer == null){
            BbqProducer producer = new BbqProducer();
            bbqProducer = producer;
        }
        return bbqProducer;
    }

    public void send(BbqSendMessage msg) {
        msg.setConnectionType(ConnectionTypeEnum.PRODUCER.getCode());
        msg.setTopic(Constant.TOPIC_1);
        BbqProducer.channelFuture.channel().writeAndFlush(msg);
    }

    private BbqProducer() {
        /**
         * 服务发现
         */
        routeFindThreadStart();
        /**
         * 等待broker信息更新
         */
        waitRefresh();
        /**
         * 启动
         */
        connect();
    }

    private void waitRefresh(){
        while (brokerInfo.isEmpty()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait");
        }
    }

    private void connect() {
        BrokerData brokerData = selectOneBroker();
        String brokerHost = brokerData.getIp();
        int brokerPort = brokerData.getPort();
        ChannelFuture future = RemotingClient.connect(ProducerRecived.class, brokerHost, brokerPort);
        channelFuture = future;
        System.out.println("producer connect success!");
    }


    private void routeFindThreadStart() {
        RouteFind routeFind = new RouteFind(Constant.NAME_SERVER_HOST, Constant.NAME_SERVER_PORT);
        ThreadPoolUtil.excute(routeFind);
        System.out.println("producer brokerRefreshThread success!");
    }

    private BrokerData selectOneBroker() {
        List<BrokerData> list = new ArrayList<>(brokerInfo.values());
        int i = random.nextInt(list.size());
        return list.get(i);
    }

    public static void refreshBrokerInfo(BbqConfirmMessage confirmMessage){
        String msgObj = confirmMessage.getMsgObj();
        Map<String, BrokerData> map = JSON.parseObject(msgObj,
                new TypeReference<Map<String, BrokerData>>() {});
        for (Map.Entry<String, BrokerData> entry : map.entrySet()){
            brokerInfo.put(entry.getKey() , entry.getValue());
        }
    }

}
