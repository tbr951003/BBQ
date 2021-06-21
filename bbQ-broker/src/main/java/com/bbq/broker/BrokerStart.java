package com.bbq.broker;

import com.bbq.common.Constant;
import com.bbq.common.ThreadPoolUtil;
import com.bbq.remoting.RemotingServer;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class BrokerStart {


    public static void main(String[] args) {
        new BrokerStart().run();
    }

    public void run() {
        //启动服务
        RemotingServer.start(Dispatcher.class, Constant.BROKER_PORT_1);

        //服务注册
        Register.doRegister();

        //心跳
        ThreadPoolUtil.excute(new HeartBeat());


        System.out.println("broker start succ!");
    }
}
