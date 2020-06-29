package com.itzq.spring.GcRoot;

import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * @author wangzq
 * @create 2020-06-29 20:08
 */
public class DirectBufferMemory {
    public static void main(String[] args) {

    }

    private static void directbu() {
        /**
         * java.lang.OutOfMemoryError: Direct buffer memory
         * -XX:MaxDirectMemorySize=5m -XX:+PrintFlagsFinal
         */
        System.out.println(VM.maxDirectMemory()/1024/1024);
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(6*1024*1024);
    }
}
