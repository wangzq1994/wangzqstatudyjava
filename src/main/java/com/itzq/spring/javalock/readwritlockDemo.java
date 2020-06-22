package com.itzq.spring.javalock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangzq
 * @create 2020-06-22 15:07
 */
public class readwritlockDemo {
    volatile Map<String,Object> map=new HashMap<>();
    ReadWriteLock lock=new ReentrantReadWriteLock();
    public void myput(String key,String value){
        lock.writeLock().lock();
        try {
        System.out.println(Thread.currentThread().getName()+"==来put缓存开始"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"==来put缓存结束啦"+key);
        } finally {
            lock.writeLock().unlock();
        }
    }
    public void myget(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"==来get"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"==来get结束"+key);
        } finally {
            lock.readLock().unlock();
        }

    }

    public static void main(String[] args) {
        readwritlockDemo demo=new readwritlockDemo();
        for(int i=0; i<10; ++i){
            final int tmp = i;
            new Thread(()->{
                demo.myput(String.valueOf(tmp),String.valueOf(tmp));
            },String.valueOf(i)).start();
        }

        for(int i=0; i<10; ++i){
            final int tmp = i;
            new Thread(()->{
                demo.myget(String.valueOf(tmp));
            },String.valueOf(i)).start();
        }
    }
}
