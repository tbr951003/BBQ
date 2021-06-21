package com.bbq.common;

import java.io.Serializable;

/**
 * by borong
 * ~
 * 2021-06-04
 **/
public class BbqConfirmMessage implements Serializable {



    /**
     * 确认标 ok error
     */
    private String result;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 消息体
     */
    private String msgObj;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getMsgObj() {
        return msgObj;
    }

    public void setMsgObj(String msgObj) {
        this.msgObj = msgObj;
    }


    @Override
    public String toString() {
        return "BbqConfirmMessage{" +
                "result='" + result + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", msgObj='" + msgObj + '\'' +
                '}';
    }
}
