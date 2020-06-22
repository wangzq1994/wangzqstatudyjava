package com.itzq.spring.javalock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

class Mylock{
    AtomicReference<Thread> atomicReference=new AtomicReference<>();
    public void mylock(){
        System.out.println(Thread.currentThread().getName()+"尝试获得锁");
        while (!atomicReference.compareAndSet(null,Thread.currentThread())){
            //System.out.println("");
        }
        System.out.println(Thread.currentThread().getName()+"拿到锁啦....");

    }
    public void myunlock(){
        //采用原子引用为自己 则cas修改自己为null   也就是释放锁
        atomicReference.compareAndSet(Thread.currentThread(),null);
        System.out.println(Thread.currentThread().getName()+"释放锁");
    }
}
/**
 * @author wangzq
 * @create 2020-06-22 14:51
 */
public class spinLockDemo {
    /**
     * 手写自旋锁
     * 自旋锁的核心：while+cas+原子引用线程
     *
     * A线程加锁，一顿操作5秒钟，解锁。B线程一直自旋等待A线程释放锁，然后获取锁。
     * 打印结果：
     * A尝试获取锁
     * A成功获取锁
     * A一顿操作5秒...
     * B尝试获取锁
     * A释放锁
     * B成功获取锁
     * B一顿操作
     * B释放锁
     */
    public static void main(String[] args) {
        Mylock mylock=new Mylock();
        new Thread(()->{
            mylock.mylock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁5秒");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"准备释放锁");
            }catch (Exception e){
                e.getMessage();
            }finally {
                mylock.myunlock();
            }
        },"AAAA").start();
        new Thread(()->{
            mylock.mylock();
            try {
                TimeUnit.SECONDS.sleep(1);//睡一秒保证AAAA先获得锁
                System.out.println(Thread.currentThread().getName()+"获得锁5秒");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"准备释放锁");
            }catch (Exception e){
                e.getMessage();
            }finally {
                mylock.myunlock();
            }
        },"BBBB").start();
    }
}
