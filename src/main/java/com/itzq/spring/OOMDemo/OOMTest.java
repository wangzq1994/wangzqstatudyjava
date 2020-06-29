package com.itzq.spring.OOMDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzq
 * @create 2020-06-29 19:28
 */
public class OOMTest {
    public static void main(String[] args) {
        overhead();

    }
    public static void StackOverflowErrorTest(){
        /**
         * 递归调用产生 StackOverflowError
         * 栈空间默认 512K-1024K
         */
        StackOverflowErrorTest();
    }

    public static void HeapSpace(){
        /**
         *java.lang.OutOfMemoryError: Java heap space
         * 修改堆的大小便于测试内存溢出
         * -Xms5m -Xmx5m
         */
        byte[] bytes=new byte[8*1024*1024];
    }
    public static void overhead(){
        /**
         * java.lang.OutOfMemoryError: GC overhead limit exceeded
         * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=2m
         */
        int i=0;
        List<String> list=new ArrayList<>();
        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
