package com.chanzany.interview_secondary.juc_06_BlockingQueue;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyData {
    int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void produce() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;

//            TimeUnit.MILLISECONDS.sleep(100);

            System.out.println(Thread.currentThread().getName() + "\t生产了一个产品，当前产品数量:" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void consume() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
//            TimeUnit.MILLISECONDS.sleep(100);

            System.out.println(Thread.currentThread().getName() + "\t消费了一个产品，当前产品数量:" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class ProducerConsumer_Traditional {
    public static void main(String[] args) {
        MyData myData = new MyData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                myData.produce();
            }
        }, "麦当劳").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                myData.produce();
            }
        }, "肯德基").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                myData.consume();
            }
        }, "老王").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                myData.consume();
            }
        }, "老陈").start();
    }
}
