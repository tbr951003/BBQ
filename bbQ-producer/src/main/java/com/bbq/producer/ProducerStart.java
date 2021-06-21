package com.bbq.producer;

import com.bbq.common.BbqSendMessage;

/**
 * @program: bbQ
 * @description: 生产者启动类
 * @author: borong
 * @create: 2021-05-23
 **/
public class ProducerStart {

    public static void main(String[] args) {
        
        BbqProducer producer = BbqProducer.init();

        for (int i = 0 ; i < 1000 ; i++){
            BbqSendMessage msg = new BbqSendMessage();
            msg.setMessage("hello BBQ ---" + i);
            System.out.println(msg.getMessage());
            producer.send(msg);
        }

        System.out.println("producer sendMessage success!");

    }

}
