package com.chanzany.interview_secondary.juc01_volatile;

public class SingletonDemo {

    private volatile static SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo()");
    }
    public static SingletonDemo getInstance(){
        if (instance==null){
            instance = new SingletonDemo();
        }
        return instance;
    }

    //DCL(Double Check Lock)双端检索机制不一定线程安全，原因是有指令重排序的存在
    // 加入volatile可以禁止指令重排
    public static SingletonDemo getInstance2(){
        if (instance==null){
            synchronized (SingletonDemo.class){
                if (instance==null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        //单线程下适用
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());


        //多线程下，情况发生很大的变化
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
//                 SingletonDemo.getInstance();
                 SingletonDemo.getInstance2();
            },String.valueOf(i)).start();
        }
    }
}
