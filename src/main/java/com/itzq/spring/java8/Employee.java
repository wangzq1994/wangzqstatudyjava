package com.itzq.spring.java8;

/**
 * @author wangzq
 * @create 2020-06-08 16:51
 */
public class Employee {
    private String name ;
    private Integer age ;
    private Double wages ;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", wages=" + wages +
                '}';
    }

    public Employee(String name, Integer age, Double wages) {
        this.name = name;
        this.age = age;
        this.wages = wages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWages() {
        return wages;
    }

    public void setWages(Double wages) {
        this.wages = wages;
    }
}
