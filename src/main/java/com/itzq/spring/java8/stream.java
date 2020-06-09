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
        generate.limit(10).forEach(System.out::println);
    }

    List<Employee> employees=Arrays.asList(
            new Employee("张三",18,9999.99),
            new Employee("李四",58,5555.55),
            new Employee("王五",26,3333.33),
            new Employee("赵六",36,6666.66),
            new Employee("田七",12,8888.88),
            new Employee("田七",12,8888.88)
    );
    /**
     * 中间操作
     * 多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，
     * 否则中间操作不会执行任何的处理！而在终止操作时一次性处理，成为“惰性求值”。
       筛选与切片
     *  filter--接收Lambda，从流中排除某些元素。
     *  limit--截断流，使其元素不超过给定数量。
     *  skip(n)--跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n) 互补
     *  distinct--筛选，通过流所生成元素的 hashCode() 和 equals() 去掉重复元素
     */
    public void test3(){
        employees.stream().filter(x->x.getAge()>35).forEach(System.out::println);

    }
    //发现“短路”只输出了两次，说明只要找到 2 个 符合条件的就不再继续迭代
    @Test
    public void test2(){
        employees.stream().filter((x)->{
            System.out.println("过滤过滤");
            return x.getWages()>0;}).limit(2).forEach(System.out::println);

    }

}
