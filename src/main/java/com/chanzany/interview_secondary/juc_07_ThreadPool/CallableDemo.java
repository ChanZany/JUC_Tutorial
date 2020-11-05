package com.chanzany.interview_secondary.juc_07_ThreadPool;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Callable接口是Runnable接口的变体，其call方法等同与run方法，但是call方法有返回值
 */

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{
    static AtomicInteger callNum = new AtomicInteger();
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t call....");
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("计算完成。。。。");
        return callNum.incrementAndGet();
    }


}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread2());

//        for (int i = 0; i < 3; i++) {
//            new Thread(futureTask,"Thread"+ i).start();
//            System.out.println(futureTask.get());
//        }
//        //Thread0	 call.... 发现只调用一次call方法：同一个FutureTask对象只会被调用一次，结果会复用
//        System.out.println("============");
//        System.out.println(futureTask.get());
        new Thread(futureTask, "AA").start();
        new Thread(futureTask2, "BB").start();
//        int result2 = futureTask.get();//一旦调用，就阻塞其他线程，直到拿到结果才退出，如无必要，建议放在最后

        System.out.println(Thread.currentThread().getName());
        int result1 = 100;
        int result2 = 0;

        while (!futureTask.isDone()){
             result2 = futureTask.get();
        }
        System.out.println("Final Result:"+(result1+result2));


    }
}
