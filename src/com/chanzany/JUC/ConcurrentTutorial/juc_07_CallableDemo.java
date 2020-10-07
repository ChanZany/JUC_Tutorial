package com.chanzany.JUC.ConcurrentTutorial;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*class MyThread implements Runnable{
    @Override
    public void run() {

    }
}*/

/**
 * 实现多线程的方法：
 * 1. 继承Thread类
 * 2. 实现Runnable接口
 * 3. 实现Callable接口
 * 4. 通过线程池
 */
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("...come in call method");
        return 1024;
    }
}

public class juc_07_CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Thread t1 = new Thread(new MyThread(),"A");
        FutureTask<Integer> futureTask = new FutureTask(new MyThread());
        new Thread(futureTask,"A").start();
        Integer result = futureTask.get();
        System.out.println(result);
    }
}

