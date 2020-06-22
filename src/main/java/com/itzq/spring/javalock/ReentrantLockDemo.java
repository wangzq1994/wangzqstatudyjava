package com.itzq.spring.javalock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class synchronizedResource{
    public synchronized  void m1(){
        System.out.println(Thread.currentThread().getName()+"woshi m1");
        m2();
    }
    public synchronized  void m2(){
        System.out.println(Thread.currentThread().getName()+"woshi m2");
    }
}
class lockResource{
    Lock lock=new ReentrantLock();
    public  void m1(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"woshi m1");
            m2();
        }finally {
            lock.unlock();
        }
    }
    public  void m2(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"woshi m2222");
        }finally {
            lock.unlock();
        }
    }
}
/**
 * @author wangzq
 * @create 2020-06-22 14:25
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        //synchronizedtest();
        lockResource a=new lockResource();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                a.m1();
            },String.valueOf(i)).start();
        }
    }

    private static void synchronizedtest() {
        synchronizedResource a=new synchronizedResource();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                a.m1();
            },String.valueOf(i)).start();
        }
    }
}
