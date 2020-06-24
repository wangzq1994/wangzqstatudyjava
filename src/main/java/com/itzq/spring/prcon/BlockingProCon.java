package com.itzq.spring.prcon;

import org.springframework.util.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 基于阻塞队列的生产者消费者
 * 题目：当flag=true时，启动生产者和消费者线程，一直循环做生产消费，
 * 当flag=false时，停止所有线程，程序运行结束。
 */
class MyResource{
    //消费者生产者 启动停止标志位
    private volatile boolean falage=true;
    private BlockingQueue<String> blockingQueue=null;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    //面向接口方式编程 让使用者传入具体的实现类
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        //获取到实际传入的子类name 便于排错
        System.out.println(blockingQueue.getClass().getName());
    }
    public void myProducer() throws InterruptedException {
        //生产者
        int id;
        String cake;
        while (falage){
            id=atomicInteger.incrementAndGet();
            cake="好吃的蛋糕"+id;
            boolean offer = blockingQueue.offer(cake, 2l, TimeUnit.SECONDS);
            if(offer){
                System.out.println(Thread.currentThread().getName()+"==生产蛋糕成功入队..."+cake);
            }else{
                System.out.println(Thread.currentThread().getName()+"==生产蛋糕入队失败<<<");
            }
            // 生产者产的慢，产一个消费者就吃一个。
            // 生产者产的快，就会入队失败，等消费者来吃。
            //TimeUnit.SECONDS.sleep(1);
        }
    }
    public void myConsumer() throws InterruptedException {
        while (falage){
            String cake = blockingQueue.poll(2l, TimeUnit.SECONDS);
            if(StringUtils.isEmpty(cake)){
                System.out.println(Thread.currentThread().getName()+"\t消费等待超过2秒，消费失败");
                //通知生产者也退出生产
                falage=false;
                return;

            }else {
                System.out.println(Thread.currentThread().getName()+"\t成功出队\t"+cake);
            }
        }
    }
    public void Stop(){
        falage=false;
    }
}
/**
 * @author wangzq
 * @create 2020-06-24 10:47
 */
public class BlockingProCon {
    public static void main(String[] args) throws InterruptedException {

        MyResource rs=new MyResource(new ArrayBlockingQueue<String>(3));
        new Thread(()->{
            System.out.println("启动"+Thread.currentThread().getName());
            try {
                rs.myProducer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者").start();
        new Thread(()->{
            System.out.println("启动"+Thread.currentThread().getName());
            try {
                rs.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费者").start();
        //5s后停止生产和消费
        TimeUnit.SECONDS.sleep(1);
        rs.Stop();
    }
}
