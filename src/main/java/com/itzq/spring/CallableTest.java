package com.itzq.spring;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class mycallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("任务执行了");
        return 1;
    }
}
/**
 * @author wangzq
 * @create 2020-06-11 16:05
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Thread 类无法直接接受 callable 的实例 需要包装一下
        FutureTask<Integer> ts=new FutureTask(new mycallable());
        new Thread(ts,"AA").start();
        Integer integer = ts.get();
        System.out.println(integer);


    }
}
