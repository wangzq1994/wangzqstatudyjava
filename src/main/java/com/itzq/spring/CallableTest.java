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
        FutureTask<Integer> futureTask=new FutureTask(new mycallable());
        new Thread(futureTask,"AA").start();
        //同一个futureTask被多次执行 只会执行一次
        //new Thread(futureTask,"BB").start();

        //查看futureTask 是否执行完毕
        while (futureTask.isDone()){

        }
        // get方法建议放在最后调用：因为
        // get方法会要求返回Callable计算结果，如果没有完成，会导致其他线程阻塞，直到计算出结果返回。
        Integer integer = futureTask.get();
        System.out.println(integer);


    }
}
