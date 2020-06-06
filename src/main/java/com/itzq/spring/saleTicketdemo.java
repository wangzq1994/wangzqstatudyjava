package com.itzq.spring;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int number = 30;
    Lock lock=new ReentrantLock();
    public  void sale(){
        lock.lock();
        try {
            if (number>0){
                System.out.println(Thread.currentThread().getName()+"\t 当前卖出的第=="+number-- +"还剩==="+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           lock.unlock();
        }
    }
}

public class saleTicketdemo {

    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"AAA").start();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"BBB").start();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"CCC").start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "AAAA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "BBBB").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "CCCC").start();*/
    }
}
