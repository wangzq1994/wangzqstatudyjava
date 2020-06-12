package com.itzq.spring.prcon;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;

/**
 * @author wangzq
 * @create 2020-06-12 14:43
 */
public class TestBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(1);
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    blockingQueue.put("123");
                    blockingQueue.put("456");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAAA").start();

        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    String take = blockingQueue.take();
                    System.out.println(take);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"CCCC").start();


    }
    @Test
    public void test(){
        Executors.newFixedThreadPool(1);//固定大小的线程池
        Executors.newSingleThreadExecutor();// 创建单线程池
        Executors.newCachedThreadPool();// 创建可缓存的线程池
        Executors.newScheduledThreadPool(100);


    }
}
