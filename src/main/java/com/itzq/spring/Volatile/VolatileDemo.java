package com.itzq.spring.Volatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class myres{
    int num=0;
    //volatile int num=0;
    AtomicInteger cas=new AtomicInteger();
    public void changge(){
        this.num=2020;
    }
    public  void add1(){
        num++;
    }
    public  void addcas(){
        cas.getAndIncrement();
    }
    
}
/**
 * @author wangzq
 * @create 2020-06-18 11:08
 */
public class VolatileDemo {
    /**
     * 演示volatile保证内存的可见性
     * 演示volatile 不保证原子性
     */
    public static void main(String[] args) {
        //kjxdemo();
        myres my=new myres();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    my.addcas();
                }
            },"BBB").start();
        }
        while (Thread.activeCount()>2){
            // 线程礼让
            Thread.yield();
        }
        System.out.println("number==="+my.cas);
    }

    private static void kjxdemo() {
        myres my=new myres();
        new Thread(()->{
            System.out.println("修改数值线程进来了");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            my.changge();
            System.out.println("修改数值完成");
        },"AAA").start();

        while (my.num==0){
            // 线程礼让
            Thread.yield();
        }
        System.out.println("main 线程打印num  "+my.num);
    }

}
