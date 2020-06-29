package com.itzq.spring.GcRoot;

import java.util.concurrent.TimeUnit;

/**
 * @author wangzq
 * @create 2020-06-29 13:54
 */
public class GcRootDemo {
    /**
     * @param args
     * @throws InterruptedException
     * -XX:+PrintGCDetails   开启GC日志打印
     *-XX:MetaspaceSize=10m  设置元空间大小默认21m
     * -Xms10m -Xmx10m -XX:MetaspaceSize=10m -XX:+PrintFlagsFinal
     * -XX:InitialHeapSize=10m -Xmx10m -XX:MetaspaceSize=10m  -XX:+PrintCommandLineFlags
     * -XX:+PrintGCDetails -XX:SurvivorRatio=4 -XX:NewRatio=3
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hellGC=================");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
