package com.bbq.namesrv;

import com.bbq.common.Constant;
import com.bbq.remoting.RemotingServer;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class NameSrvStart {

    public static void main(String[] args) {
        new NameSrvStart().run();
    }

    private void run(){

        //启动
        RemotingServer.start(NameSrvDispatcher.class, Constant.NAME_SERVER_PORT);

        System.out.println("NameServer Start succ!");

    }

}
