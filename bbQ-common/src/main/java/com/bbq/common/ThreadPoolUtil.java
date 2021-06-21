package com.bbq.common;

import java.util.concurrent.*;

/**
 * by borong
 * ~
 * 2021-06-21
 **/
public class ThreadPoolUtil {

    private static final ExecutorService executor = Executors.newFixedThreadPool(3);


    private ThreadPoolUtil(){

    }

    public static void excute(Runnable task){
        executor.execute(task);
    }


}
