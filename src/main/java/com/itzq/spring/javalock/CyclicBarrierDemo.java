package com.itzq.spring.javalock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wangzq
 * @create 2020-06-22 15:58
 */
public class CyclicBarrierDemo {
    CyclicBarrier cyclicBarrier=new CyclicBarrier(5,()->{
        System.out.println("集中点，此时所有线程完成ready操作");
    });

    public static void main(String[] args) {
        CyclicBarrierDemo demo=new CyclicBarrierDemo();
        for(int i=0; i<5; ++i){
            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"\t"+"执行ready");
                try {
                    demo.cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"执行continue");
            },String.valueOf(i)).start();
        }
    }
}
