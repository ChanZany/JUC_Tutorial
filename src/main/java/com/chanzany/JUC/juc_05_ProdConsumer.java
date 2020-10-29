package com.chanzany.JUC;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirCondition {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();// this.wait();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();// this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() throws Exception {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();// this.wait();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();// this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



    /*public synchronized void increment() throws Exception {
        //1. 判断
        while (number != 0) {
            this.wait();
        }
        //2. 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3. 通知
        this.notifyAll();

    }

    public synchronized void decrement() throws Exception {
        while(number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }*/


}

/**
 * 题目:两个线程，可以操作初始值为0的一个变量
 * 实现一个线程对该变量+1.一个线程对该变量-1，
 * 实现交替，来10轮，变量初始值为0
 * <p>
 * 1. 高内聚，低耦合前提下，线程操作资源类
 * 2. 判断/干活/通知
 * 题目:4个线程，可以操作初始值为0的一个变量,两个生产者，两个消费者
 * 发现出现了问题
 * 3. 防止虚假唤醒(使用while进行判断)
 * <p>
 * 总结：多线程编程套路+while判断+新版写法
 */
public class juc_05_ProdConsumer {

    public static void main(String[] args) {
        AirCondition resource = new AirCondition();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "producer").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "producer2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "consumer2").start();
    }
}
