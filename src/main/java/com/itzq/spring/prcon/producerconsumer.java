package com.itzq.spring.prcon;

import java.util.PrimitiveIterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangzq
 * @create 2020-06-10 14:25
 */
class resource {
    private Integer number=0;
    public synchronized  void reduce(){
        while (number==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"==来消费了=="+number);
        this.notifyAll();
    }
    public synchronized  void increase(){
        while (number!=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"==来生产了=="+number);
        this.notifyAll();
    }
}
class resource2 {
    private Integer number=0;
    Lock lock=new ReentrantLock();
    Condition condition = lock.newCondition();
    public void reduce(){
        lock.lock();
        try {
            while (number==0){
                //this.wait();
                condition.await();
            }
        number--;
        System.out.println(Thread.currentThread().getName()+"==新来消费了=="+number);
        //this.notifyAll();
        condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void increase(){
        lock.lock();
        try {
            while (number!=0){
                //this.wait();
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"==新来生产了=="+number);
            //this.notifyAll();
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
public class producerconsumer {
    public static void main(String[] args) {
        //resource2 resource=new resource2();
        resource resource=new resource();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                resource.reduce();
            }
        },"AAA").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                resource.increase();
            }
        },"BBB").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                resource.reduce();
            }
        },"CCC").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                resource.increase();
            }
        },"DDD").start();
    }
}
