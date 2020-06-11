package com.itzq.spring;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangzq
 * @create 2020-06-11 15:29
 */
class printdemo{
    private  int numb=1; //A : 1 B : 2 C : 3
    Lock lock=new ReentrantLock();
    Condition c1=lock.newCondition();
    Condition c2=lock.newCondition();
    Condition c3=lock.newCondition();
    public void printtest5(){
        lock.lock();
        try {
            while (numb!=1){
                c1.await();
            }
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 打印了 "+i);
            }
            numb=2;//修改标志位
            c2.signal();//精准唤醒B线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printtest10(){
        lock.lock();
        try {
            while (numb!=2){
                c2.await();
            }
            for (int i = 1; i <= 2; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 打印了 "+i);
            }
            numb=3;//修改标志位
            c3.signal();//精准唤醒B线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printtest15(){
        lock.lock();
        try {
            while (numb!=3){
                c3.await();
            }
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 打印了 "+i);
            }
            numb=1;//修改标志位
            c1.signal();//精准唤醒B线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class conditiondemo {
    /**
     * 线程顺序调度精确唤醒
     * 使用多个condition实现
     * A线程打印5次后 B线程 打印10次  然后C 线程打印15 次 循环10次
     */


    public static void main(String[] args) {
        printdemo printdemo=new printdemo();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                printdemo.printtest5();
            }
        },"AAA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                printdemo.printtest10();
            }
        },"BBB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                printdemo.printtest15();
            }
        },"CCC").start();
    }
}
