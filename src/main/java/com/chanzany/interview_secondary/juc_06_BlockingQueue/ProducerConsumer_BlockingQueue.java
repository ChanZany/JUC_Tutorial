package com.chanzany.interview_secondary.juc_06_BlockingQueue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyData2 {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public MyData2(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void produce() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\tFLAG=FALSE.生产结束");
    }

    public void consume() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2s没有消费成功，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列" + result + "成功");
        }
    }

    public void stop() throws Exception{
        this.FLAG=false;
    }
}

public class ProducerConsumer_BlockingQueue {
    public static void main(String[] args) throws Exception {

        MyData2 myData = new MyData2(new ArrayBlockingQueue<String>(10));

        new Thread(() -> {
            try {
                myData.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "麦当劳").start();
        new Thread(() -> {
            try {
                myData.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "肯德基").start();

        new Thread(() -> {
            try {
                myData.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "老王").start();

        new Thread(() -> {
            try {
                myData.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }, "老陈").start();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("5秒后，停止生产和消费行为");
        myData.stop();
    }
}
