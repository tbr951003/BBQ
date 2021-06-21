package com.bbq.common.eum;


public enum BrokerMsgTypeEnum {

    PRODUCER_SEND("1" , "生产者发送的消息"),
    CONSUMER_PULL("2" , "消费者拉取请求"),


    ;


    BrokerMsgTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static BrokerMsgTypeEnum codeOf(String code){
        BrokerMsgTypeEnum[] brokerMsgTypeEnums = BrokerMsgTypeEnum.values();
        for (BrokerMsgTypeEnum brokerMsgTypeEnum : brokerMsgTypeEnums){
            if (brokerMsgTypeEnum.getCode().equals(code)){
                return brokerMsgTypeEnum;
            }
        }
        return null;
    }

}
