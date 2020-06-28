package com.itzq.spring.Volatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

class User{
    private String name;
    AtomicReference<Integer> aoo=new AtomicReference<>(0);
    AtomicStampedReference<Integer> aoo2=new AtomicStampedReference<>(0,1);
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class CasDemo {
    /**
     * ABA 问题解决
     * @param args
     */
    public static void main(String[] args) {
        //AtomicStampedReference 解决ABA  问题
        User user=new User("212");
        new Thread(()->{
            //休息三秒让BB线程和AA线程同时拿到相同的版本号
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+"==修改前版本="+user.aoo2.getStamp());
                System.out.println(user.aoo2.compareAndSet(0,100,user.aoo2.getStamp(),user.aoo2.getStamp()+1)+"=="+user.aoo2.getReference());
                System.out.println(user.aoo2.compareAndSet(100,0,user.aoo2.getStamp(),user.aoo2.getStamp()+1)+"=="+user.aoo2.getReference());
                System.out.println(Thread.currentThread().getName()+"==修改后版本="+user.aoo2.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();
        new Thread(()->{
            try {
                int stamp = user.aoo2.getStamp();
                System.out.println(Thread.currentThread().getName()+"==修改前版本="+stamp);
                //休息3秒保证AA线程执行完ABA
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"==睡眠后修改前版本="+user.aoo2.getStamp());
                System.out.println(user.aoo2.compareAndSet(0,2019,stamp,user.aoo2.getStamp()+1)+"=="+user.aoo2.getReference());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BB").start();

    }

    private static void teseABA() {
        //AtomicReference 演示ABA问题存在
        User user=new User("zzz");
        new Thread(()->{
            System.out.println(user.aoo.compareAndSet(0,100));
            System.out.println(user.aoo.compareAndSet(100,0));
        },"AA").start();
        new Thread(()->{
            try {
                //让BB线程 睡3秒 保证AA线程执行完ABA
                TimeUnit.SECONDS.sleep(3);
                System.out.println(user.aoo.compareAndSet(0,2020)+"===="+user.aoo.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }

    private static void testatomicreference() {
        //演示原子包装类 包装任意类型
        User user1=new User("z1");
        User user2=new User("z2");
        AtomicReference<User> ao=new AtomicReference<>(user1);
        System.out.println(ao.compareAndSet(user1,user2)+"=="+ao.get().toString());
        System.out.println(ao.compareAndSet(user1,user2)+"=="+ao.get().toString());
    }

    private static void testcas() {
        //验证cas 效果
        AtomicInteger ai=new AtomicInteger(5);
        System.out.println(ai.compareAndSet(5,100)+"===="+ai.get());
        //演示ABA问题
        //System.out.println(ai.compareAndSet(100,5)+"===="+ai.get());
        System.out.println(ai.compareAndSet(5,200)+"===="+ai.get());
    }
}
