package com.itzq.spring.prcon;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
   private  String lock1;
   private  String lock2;

    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName()+"获得锁 "+lock1+"等到锁"+lock2);
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){

            }
        }
    }
}
/**
 * @author wangzq
 * @create 2020-06-24 19:44
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lo1="LOCKA";
        String lo2="LOCKB";
        new Thread(new HoldLockThread(lo1,lo2)).start();
        new Thread(new HoldLockThread(lo2,lo1)).start();
    }
}
