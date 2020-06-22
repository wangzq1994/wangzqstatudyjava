package com.itzq.spring.javalock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author wangzq
 * @create 2020-06-22 16:04
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        haidilao();

    }

    private static void haidilao() {
        Semaphore semaphore=new Semaphore(3);
        for(int i=0; i<5; ++i){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"一顿干活");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"释放");
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
