package com.itzq.spring;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author wangzq
 * @create 2020-06-06 16:39
 */
public class test {



    static class MyThread implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName()+"\t调用call方法");
            TimeUnit.SECONDS.sleep(1);
            return 1024;
        }
    }




    public static void main(String[] args) throws InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        Thread thread = new Thread(() -> {
            System.out.println("1111111");
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("222222");
        });
        thread.start();
        thread.join();


        System.out.println("3333333");
    }

}
