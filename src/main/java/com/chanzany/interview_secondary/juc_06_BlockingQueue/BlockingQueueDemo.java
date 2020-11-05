package com.chanzany.interview_secondary.juc_06_BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1. 为什么出现阻塞队列？
 * 有些场景下：必须要阻塞，不得不阻塞,如排号休息区等吃火锅
 *
 * 2. 如何管理阻塞队列?
 * 当阻塞队列是空时,从队列中获取元素的操作将会被阻塞.
 * 当阻塞队列是满时,往队列中添加元素的操作将会被阻塞.
 * 同样
 * 试图往已满的阻塞队列中添加新圆度的线程同样也会被阻塞,直到其他线程从队列中移除一个或者多个元素或者
 * 全清空队列后使队列重新变得空闲起来并后续新增.
 *
 *     异步+通知
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add(1));
        System.out.println(blockingQueue.add("str"));
        System.out.println(blockingQueue.add(true));
//        System.out.println(blockingQueue.add('a'));


        System.out.println(blockingQueue.remove(1));
        System.out.println(blockingQueue.remove("str"));
        System.out.println(blockingQueue.remove(true));
        System.out.println(blockingQueue.remove('a'));

        /*System.out.println(blockingQueue.offer(1));
        System.out.println(blockingQueue.offer("str"));
        System.out.println(blockingQueue.offer(true));
        System.out.println(blockingQueue.offer('a'));*/

        /*System.out.println(blockingQueue.offer(1,1000, TimeUnit.MILLISECONDS));
        System.out.println(blockingQueue.offer("str",1000, TimeUnit.MILLISECONDS));
        System.out.println(blockingQueue.offer(true,1000, TimeUnit.MILLISECONDS));
        System.out.println(blockingQueue.offer('a',1000, TimeUnit.MILLISECONDS));*/



    }

}
