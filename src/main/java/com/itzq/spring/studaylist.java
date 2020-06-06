package com.itzq.spring;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class studaylist {
    public static void main(String[] args) {
        List list= new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList<>());//new Vector();//new ArrayList();
        for (int i = 0; i <30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }


    }

}
