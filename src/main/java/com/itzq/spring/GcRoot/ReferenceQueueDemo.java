package com.itzq.spring.GcRoot;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author wangzq
 * @create 2020-06-29 17:01
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) {
        /**
         * 弱引用
         */
        ReferenceQueue<Object> referenceQueue=new ReferenceQueue<>();
        Object o1=new Object();
        WeakReference<Object> weakReference=new WeakReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("===============");
        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
        //回收时会被放到引用队列
        System.out.println(referenceQueue.poll());
    }
}
