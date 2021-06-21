package com.bbq.consumer;


import com.bbq.common.ThreadPoolUtil;

/**
 * by borong
 * ~
 * 2021-06-15
 **/
public class ConsumerStart {



    public static void main(String[] args) {
        new ConsumerStart().start();
    }

    private void start() {
        //服务发现
        ThreadPoolUtil.excute(new RouteFind());

        //等待brokerMap更新
        ConsumerManager.waitRefresh();

        //启动consumer
        ConsumerManager.consumerStart();

    }

    

}
