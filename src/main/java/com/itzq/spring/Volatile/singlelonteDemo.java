package com.itzq.spring.Volatile;

/**
 * @author wangzq
 * @create 2020-06-18 19:29
 */
public class singlelonteDemo {
    //private static singlelonteDemo Instance= null;
    private static volatile singlelonteDemo Instance= null;
    private singlelonteDemo(){
        System.out.println(Thread.currentThread().getName()+"我是构造方法====");
    }
    //public static synchronized singlelonteDemo getInstance(){ //重锁不用
    public static  singlelonteDemo getInstance(){
        if(Instance==null){
            synchronized (singlelonteDemo.class){
                if(Instance==null){
                    Instance=new singlelonteDemo();
                }
            }
        }
        return Instance;
    }

    public static void main(String[] args) {
        //System.out.println(singlelonteDemo.getInstance()==singlelonteDemo.getInstance());
        //System.out.println(singlelonteDemo.getInstance()==singlelonteDemo.getInstance());
        //System.out.println(singlelonteDemo.getInstance()==singlelonteDemo.getInstance());
        for (int j = 0; j < 10; j++) {
            new Thread(()->{
                singlelonteDemo.getInstance();
            },String.valueOf(j)).start();
        }

    }
}
