package com.chanzany.interview_secondary.juc_07_ThreadPool;

import java.util.concurrent.*;


/*
线程池的底层构造：

public ThreadPoolExecutor(int corePoolSize, //常驻核心线程数量
                              int maximumPoolSize, // 最大线程数量
                              long keepAliveTime, // 空闲线程存活时间
                              TimeUnit unit, //时间的单位
                              BlockingQueue<Runnable> workQueue, //指定使用的阻塞队列(ArrayXXX/LinkedXXX)
                              ThreadFactory threadFactory, //创建线程使用的工厂类
                              RejectedExecutionHandler handler) // 拒绝策略：在线程池无法处理新的任务时，需要拒绝该任务的提交行为
{
        ....
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

*/

public class ThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                3, //该银行平时开放的窗口数
                5, // 该银行总共的窗口数
                2, TimeUnit.SECONDS, // 窗口空闲2秒后就关闭
                new ArrayBlockingQueue<Runnable>(2), // 候客区座位数量
                Executors.defaultThreadFactory());
        //当顾客数量>总窗口数+候客区座位数，则启动拒绝策略，大堂经理会让顾客去其他银行或过一会儿再来

        //模拟10个用户俩办理业务
        try {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理客户"+ finalI +"的业务");
                    try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace();}
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
}
