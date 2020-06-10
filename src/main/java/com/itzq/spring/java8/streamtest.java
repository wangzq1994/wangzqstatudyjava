package com.itzq.spring.java8;

import org.junit.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class streamtest {
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
     *
     *
     * 筛选与切片
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
    List<String> list=Arrays.asList("aaa","bbb","ccc","ddd");
    //中间操作
    /*
     * 映射
     * map--接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新元素。
     * flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test4(){

        list.stream().map(x->x.toUpperCase()).forEach(System.out::println);
        System.out.println("------------------------");
        employees.stream().map(x->x.getName()).forEach(System.out::println);
    }

    @Test
    public void test5(){
        Stream<Stream<Character>> stream=list.stream()
                .map(streamtest::filterChatacter);
        //stream.forEach(System.out::println);
        stream.forEach((s)->{
            s.forEach(System.out::println);
        });
        System.out.println("------------------------");
        Stream<Character> stream2=list.stream()
                .flatMap(streamtest::filterChatacter);
        stream2.forEach(System.out::println);

    }
    public static Stream<Character> filterChatacter(String str){
        List<Character> list=new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
    //中间操作
    /*
     * 排序
     * sorted()-自然排序（按照对象类实现Comparable接口的compareTo()方法 排序）
     * sorted(Comparator com)-定制排序（Comparator）
     */
    @Test
    public void test6(){
        list.stream().sorted().forEach(System.out::println);
        employees.stream().sorted((e1,e2)->{
            if(e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e2.getName());
            }else{
                return e1.getAge().compareTo(e2.getAge());
            }

        }).forEach(System.out::println);


    }
    @Test
    public void test8(){
        boolean b = employees.stream().anyMatch(x -> x.getAge()>22);
        System.out.println(b);
        boolean b1 = employees.stream().allMatch(x -> x.getAge() > 22);
        System.out.println(b1);
        boolean b2 = employees.stream().noneMatch(x -> x.getAge() < 22);
        System.out.println(b2);
        //Optional<Employee> first = employees.stream().findFirst();
        Optional<Employee> first = employees.stream().findAny();
        Employee employee = first.get();
        System.out.println(employee);
        System.out.println("------------------------");
        long count = employees.stream().count();
        System.out.println(count);
        Optional<Employee> max = employees.stream().max((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println(max.get());
        Optional<Double> min = employees.stream().map(Employee::getWages).min(Double::compare);
        System.out.println(min.get());

    }

    /*
     * 归约
     * reduce(T identity,BinaryOperator b) / reduce(BinaryOperator b)-可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test10(){
        List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
        System.out.println("--------------------------");
        Optional<Double> reduce1 = employees.stream().map(Employee::getWages).reduce(Double::sum);
        System.out.println(reduce1.get());
    }
}
