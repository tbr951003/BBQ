package com.bbq.common;

import java.io.Serializable;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class BbqRouteMessage implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 消息类型 服务注册 服务发现
     */
    private String routeMsgType;

    /**
     * brokerName
     */
    private String brokerName;

    /**
     * ip
     */
    private String ip;

    /**
     * 端口
     */
    private int port;

    /**
     * 主题信息
     *  List<String>
     */
    private String topicList;

    public String getTopicList() {
        return topicList;
    }

    public void setTopicList(String topicList) {
        this.topicList = topicList;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getRouteMsgType() {
        return routeMsgType;
    }

    public void setRouteMsgType(String routeMsgType) {
        this.routeMsgType = routeMsgType;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "BbqRouteMessage{" +
                "routeMsgType='" + routeMsgType + '\'' +
                ", brokerName='" + brokerName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
