package com.bbq.common;

import java.io.Serializable;

public class BbqSendMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 队列名称
     */
    private String topic;
    /**
     * 消息体
     */
    private String message;


    /**
     * 消息源类型
     */
    private String connectionType;


    public BbqSendMessage(String topic, String message, String connectionType) {
        this.topic = topic;
        this.message = message;
        this.connectionType = connectionType;
    }

    //默认无参构造
    public BbqSendMessage() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }


    @Override
    public String toString() {
        return "BbqSendMessage{" +
                "queueName='" + topic + '\'' +
                ", message='" + message + '\'' +
                ", connectionType='" + connectionType + '\'' +
                '}';
    }
}