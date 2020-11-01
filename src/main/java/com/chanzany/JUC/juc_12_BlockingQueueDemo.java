package com.chanzany.JUC;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * 使用场景：必须要阻塞，不得不阻塞,如排队买蛋糕
 */
public class juc_12_BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

       /*抛出异常组
       System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
       // System.out.println(blockingQueue.add("x"));//IllegalStateException: Queue full
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
       // System.out.println(blockingQueue.remove());//NoSuchElementException*/

        /*特殊值组(没有就返回Null)
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

       /* 阻塞组（满了或没有就阻塞）
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        //blockingQueue.put("a"); // 程序阻塞
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //System.out.println(blockingQueue.take()); //程序阻塞*/

       /*超时组(超时就退出阻塞)
       */
        System.out.println(blockingQueue.offer("a",3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L,TimeUnit.SECONDS));
    }
}
