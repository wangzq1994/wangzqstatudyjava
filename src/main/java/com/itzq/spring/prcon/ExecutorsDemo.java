package com.itzq.spring.prcon;

import java.util.concurrent.*;

/**
 * @author wangzq
 * @create 2020-06-24 13:57
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        //ExecutorService executorService= Executors.newFixedThreadPool(3);
        //ExecutorService executorService= Executors.newCachedThreadPool();
        //ExecutorService executorService= Executors.newSingleThreadExecutor();
        ExecutorService executorService=new ThreadPoolExecutor(
                2,
                4,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        try{
            for (int i = 0; i < 10; i++) {
                // 执行
                executorService.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t正在办理业务");
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            // 关闭
            executorService.shutdown();
        }

    }
}
