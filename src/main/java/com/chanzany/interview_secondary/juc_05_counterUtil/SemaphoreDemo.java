package com.chanzany.interview_secondary.juc_05_counterUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 抢车位
 * n个线程，m个资源
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire(); //抢车位
                    System.out.println(Thread.currentThread().getName()+"\t抢到了车位");
                    try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println(Thread.currentThread().getName()+"\t离开了车位");
                    semaphore.release(); // 释放车位
                }
            }, String.valueOf(i)).start();
        }
    }
}
