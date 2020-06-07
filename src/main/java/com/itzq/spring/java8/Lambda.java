package com.itzq.spring.java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class Lambda {
    /**
     * 一、Lambda表达式的基础语法
     * Java8中引入了一个新的操作符”->” 该操作符称为箭头操作符或Lambda操作符，箭头操作符将Lambda表达式拆分成两部分：
     * 左侧：Lambda 表达式的参数列表
     * 右侧：Lambda 表达式中所需执行的功能，即 Lambda 体
     */
    @Test
    public void test(){
        //通过匿名内部类的方式实现接口
        new Runnable() {
            @Override
            public void run() {
                System.out.println("测试============");
            }
        }.run();
//--------------------------------------------------
        //使用lambda表达式
        Runnable t=()->{
            System.out.println("我是lambda");
        };
        t.run();
    }
    /**
     * 语法格式二：有一个参数，并且无返回值
     */
    @Test
    public void test2(){
        //使用lambda表达是 实现consumer函数式接口的实现
        Consumer<String> con = (x)-> {
            System.out.println(x.length());
        };
        con.accept("不会就学");
    };
    /**
     * 语法格式三：若只有一个参数，小括号可以不写
     * x->System.out.println(x);
     *
     * 语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
     * 语法格式五：若Lambda体中只有一条语句，大括号和 return 都可以省略不写
     * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
     */
    @Test
    public void test3(){
        //使用lambda表达是 实现consumer函数式接口的实现
        Consumer<String> con = x-> System.out.println(x.length());
        con.accept("不会就学");
        Comparable<Integer> comparable= (x)->x+1;
        int n = comparable.compareTo(12);
        System.out.println(n);
        Comparator<Integer> com=(x, y)->Integer.compare(x, y);
        int compare = com.compare(12, 13);
        System.out.println(compare);
        Comparator<Integer> com2=(x, y)->Integer.compareUnsigned(x, y);
        int compare2 = com2.compare(12, 13);
        System.out.println(compare2);
    };
    /**
     *
     * java内置四大函数式接口
     *
     * Consumer<T> :消费型接口
     *          void accept(T t);
     *
     * Supplier<T> :供给型接口
     *          T get();
     *
     * Function<T,R> :函数型接口
     *          R apply(T t);
     *
     * Predicate<T> :断言型接口
     *          boolean test(T t);
     *
     */
    @Test
    public void test4(){

        //消费型
        happy(55.22,(x)->System.out.println("喜欢吃肉，消费："+x+"元"));
        //供给型接口
        //需求：产生指定个数的整数，并放入集合中
        List<Integer> list = happySupplier(2, () ->(int)(Math.random()*100));
        for (Integer o : list) {
            System.out.println(o);
        }
        //函数型接口 传入一个参数 输出一个参数
        Integer kdakdsah = happyFunction("kdakdsah", x -> x.length());
        System.out.println(kdakdsah);
        //断言接口
        ArrayList<Integer> c=new ArrayList();
        c.add(1);c.add(-1);c.add(2);c.add(-2);
        List<Integer> list1 = happyPredicate(c, x -> x > 0);
        for (Integer o : list1) {
            System.out.println(o);
        }
    };
    public void happy(double money,Consumer<Double> con){
        con.accept(money);
    }
    public List happySupplier(int nu, Supplier<Integer> con){
        ArrayList list=new ArrayList();
        if (nu>0){
            for (int i = 0; i < nu; i++) {
                list.add(con.get());
            }

        }
        return list;
    }
    public Integer happyFunction(String st, Function<String, Integer> con){
        return con.apply(st);
    }
    public List happyPredicate(List<Integer> list, Predicate<Integer> con){
        ArrayList c=new ArrayList();
        for (Integer o : list) {
            if(con.test(o)){
                c.add(o);
            }
        }
        return c;
    }
    /*
     * 一、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”（可以理解为方法引用时Lambda表达式的另一种表现形式）
     *
     * 主要有三种语法格式：
     * 对象::实例方法名
     * 类::静态方法名
     * 类::实例方法名
     *
     * 注意：
     * 1、Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
     * 2、若Lambda参数列表中的第一个参数是 实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
     *
     *
     * 二、构造器引用:
     * 格式：
     * ClassName::new
     *
     * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
     *
     *
     * 三：数组引用：
     *  Type::new;
     *
     */
    @Test
    public void test5(){
        //对象::实例方法名
        PrintStream out = System.out;
        Consumer<String> cm2=x->out.println(x);
        cm2.accept("我爱你");
        Consumer<String> cm=out::println;
        cm.accept("我爱你");
        //类::静态方法名
        //Comparator<Integer> con=(x,y)-> Integer.compare(x,y);
        Comparator<Integer> con=Integer::compare;
        int compare = con.compare(11, 12);
        System.out.println(compare);
        //类::实例方法名
        //BiPredicate<String,String> b=(x,y)->x.equals(y);
        BiPredicate<String,String> b=String::equals;
        boolean test = b.test("12", "12");
        System.out.println(test);
    };

}
