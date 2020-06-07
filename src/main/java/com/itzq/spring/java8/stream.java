package com.itzq.spring.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class stream {
    //创建Stream
    @Test
    public void test(){
        //1.可以通过Collection 系列集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();
        //2.通过 Arrays 中的静态方法stream()获取数组流
        String[] strarr=new String[10];
        Stream<String> stream = Arrays.stream(strarr);
        //3.通过Stream 类中的静态方法of()
        Stream<String> abc = Stream.of("abc", "def");
        //4.创建无限流
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
        iterate.limit(10).forEach(System.out::println);
        //生成
        Stream<Integer> generate = Stream.generate(() -> (int) (Math.random() * 100));
        generate.forEach(System.out::println);
    }
}
