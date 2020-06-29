package com.itzq.spring.GcRoot;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author wangzq
 * @create 2020-06-29 16:26
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        phantomre();

    }

    private static void phantomre() {
        /**
         * 虚引用
         */
        Object o1=new Object();
        ReferenceQueue<Object> referenceQueue=new ReferenceQueue<>();
        PhantomReference<Object> phantomReference=new PhantomReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("====================");
        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }

    private static void myWeakHashMap() {
        Integer a=new Integer(2);
        String b="12";
        WeakHashMap<Integer,String> HashMap=new WeakHashMap<>();
        HashMap.put(a,b);
        System.out.println(HashMap);
        a=null;
        System.out.println(HashMap);
        System.gc();
        System.out.println(HashMap);
    }

    private static void hashMaptest() {
        Integer a=1;
        String b="12";
        HashMap<Integer,String> HashMap=new HashMap<>();
        HashMap.put(a,b);
        System.out.println(HashMap);
        a=null;
        System.out.println(HashMap);
        System.gc();
        System.out.println(HashMap);
    }

    private static void weakre() {
        /**
         * 弱引用
         */
        Object o1=new Object();
        WeakReference<Object> weakReference=new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println("===============");
        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }

    private static void softre() {
        /**
         * 配置小内存 当内存不够时 软引用被回收
         * -Xms5m -Xmx5m -XX:+PrintGCDetails
         */

        Object o1=new Object();
        SoftReference<Object> softReference=new SoftReference<>(o1);
        System.out.println(o1+"1111");
        System.out.println(softReference.get()+"222");
        System.out.println("=================");
        o1=null;
        try {
            byte[] bytes=new byte[10*1024*1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    private static void strongRE() {
        Object o1=new Object();
        Object o2=o1;
        o1=null;
        System.gc();
        System.out.println(o2);
    }
}
