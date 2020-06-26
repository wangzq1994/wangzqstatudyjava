package com.itzq.spring;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class studaylist {

    public static void main(String[] args) {
        //List list= new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList<>());//new Vector();//new ArrayList();
        //Set list= new CopyOnWriteArraySet();
        //new HashMap<>().put();
        //new HashSet<>();

        Map<String , String> list= new ConcurrentHashMap<>();//new HashMap<>();
        for (int i = 0; i <30; i++) {
            new Thread(()->{
                list.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }

}
