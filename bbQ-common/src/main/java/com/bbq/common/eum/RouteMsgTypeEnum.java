package com.bbq.common.eum;

public enum RouteMsgTypeEnum {

    ROUTE_REGISTER("1" , "服务注册"),
    PRODUCER_ROUTE_FIND("2" , "生产者服务发现"),
    CONSUMER_ROUTE_FIND("3" , "消费者服务发现"),
    HEART_BEAT("4" , "心跳"),

    ;


    RouteMsgTypeEnum(String code, String desc) {
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

    public static RouteMsgTypeEnum codeOf(String code){
        RouteMsgTypeEnum[] routeMsgTypeEnums = RouteMsgTypeEnum.values();
        for (RouteMsgTypeEnum routeMsgTypeEnum : routeMsgTypeEnums){
            if (routeMsgTypeEnum.getCode().equals(code)){
                return routeMsgTypeEnum;
            }
        }
        return null;
    }

}