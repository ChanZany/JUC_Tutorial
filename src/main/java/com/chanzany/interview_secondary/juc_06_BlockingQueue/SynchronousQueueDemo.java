package com.chanzany.interview_secondary.juc_06_BlockingQueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\tput 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\tput 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\tput 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(()->{
            try {
                while (true){
                    try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName()+blockingQueue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
