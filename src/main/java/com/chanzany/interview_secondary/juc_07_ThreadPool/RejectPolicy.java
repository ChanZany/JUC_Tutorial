package com.chanzany.interview_secondary.juc_07_ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 1. AbortPolicy : 中断策略（默认），抛异常
 * 2. CallerRunsPolicy: 调用者运行策略，将任务回退到调用者
 * 3. DiscardOldestPolicy: 抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
 * 4. DiscardPolicy: 直接丢弃任务，不予任何处理。如果允许任务丢失，这是最好的一种方案
 */
public class RejectPolicy {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5,
                1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
                new ThreadPoolExecutor.CallerRunsPolicy()
//                new ThreadPoolExecutor.DiscardOldestPolicy()
//                new ThreadPoolExecutor.DiscardPolicy()
        );

        try {
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务"+ finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }


    }
}
