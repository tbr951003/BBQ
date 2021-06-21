package com.bbq.namesrv;

import com.bbq.common.BrokerData;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * by borong
 * ~
 * 2021-06-08
 **/
public class NameSrvManager {


    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    /**
     * brokerName ， ip映射
     */
    public static final  Map<String, BrokerData> brokerInfo = new HashMap<>();

    /**
     * topic ，brokerName 映射
     */
    public static final Map<String , Set<String>> topicInfo = new HashMap<>();


}
