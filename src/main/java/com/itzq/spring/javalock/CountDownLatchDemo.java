package com.itzq.spring.javalock;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangzq
 * @create 2020-06-22 15:56
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for(int i=0; i<6; ++i){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"执行任务");
                // 一个线程执行完任务就-1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"===执行了");
    }
}
