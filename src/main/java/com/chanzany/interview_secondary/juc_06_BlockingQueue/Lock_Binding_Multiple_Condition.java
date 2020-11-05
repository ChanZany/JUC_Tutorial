package com.chanzany.interview_secondary.juc_06_BlockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A 打印5次->B打印10次->C打印15次
 */

class PrintData {
    private Lock lock = new ReentrantLock();
    private int signal = 0;
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            //TODO判断
            while (signal!=0){
                conditionA.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "AAA");
            }
            //通知
            conditionB.signal();
            signal = 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void printB() {
        lock.lock();
        try {
            //TODO判断
            while (signal!=1){
                conditionB.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "BBB");
            }
            //通知
            conditionC.signal();
            signal = 2;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            //TODO判断
            while (signal!=2){
                conditionC.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "CCC");
            }
            //通知
            conditionA.signal();
            signal = 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class Lock_Binding_Multiple_Condition {
    public static void main(String[] args) {
        PrintData printData = new PrintData();

        for (int i = 0; i < 3; i++) {
            new Thread(printData::printA,"A").start();
            new Thread(printData::printB,"B").start();
            new Thread(printData::printC,"C").start();
        }

    }
}
