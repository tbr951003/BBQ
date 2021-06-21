package com.bbq.common.eum;

/**
 * by borong
 * ~
 * 2021-06-16
 **/
public enum ConnectionTypeEnum {

    PRODUCER("1" , "生产者"),
    CONSUMER("2" , "消费者"),


    ;


    ConnectionTypeEnum(String code, String desc) {
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
}
