package com.chanzany.JUC;



import java.util.concurrent.*;

/**
 * 在工作中单一的/固定数的/可变的三种创建线程池的方法哪个用的多？超级大坑
 * 答案是一个都不用，我们工作中只能使用自定义的
 * 原因：Executors返回的线程池对象的弊端如下
 * 1. FixedThreadPool和SingleThreadPool:
 * 允许的请求队列长度为Integer.MAX_VALUE,可能会堆积大量请求，从而导致OOM
 * 2. CachedThreadPool和ScheduledThreadPool:
 * 允许创建的线程数量为Integer.MAX_VALUE,可能会创建大量的线程，从而导致OOM
 */

public class juc_13_ThreadPoolDemo {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy() //RejectedExecutionException
//                new ThreadPoolExecutor.CallerRunsPolicy() //main	办理业务8,9
//                new ThreadPoolExecutor.DiscardPolicy() //没有办理业务 8,9
                new ThreadPoolExecutor.DiscardOldestPolicy() //没有办理业务 2,3

        );
        try {
            for (int i = 0; i < 10; i++) { //线程池的承受能力=maximumPoolSize+BlockingQueue_capacity
                int finalI = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务" + finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            threadPool.shutdown();
        }
    }

    public static void error_init() {
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个受理线程，类似一个银行5个窗口
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个受理线程
        ExecutorService threadPool = Executors.newCachedThreadPool(); //一池n个受理线程(弹性可扩容)
        try {
            //模拟10个顾客来银行办理业务
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t开始办理业务" + finalI);
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t完成业务" + finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            threadPool.shutdown();
        }
    }
}
