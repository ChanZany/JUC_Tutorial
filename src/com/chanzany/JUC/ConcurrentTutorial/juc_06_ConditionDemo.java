package com.chanzany.JUC.ConcurrentTutorial;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程之间按照顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * AA 打印5次，BB打印10次，CC打印15次
 * 接着
 * AA 打印5次，BB打印10次，CC打印15次
 * 来10轮
 */
class Resource {
    private int number = 1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    private void printNtimes(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(Thread.currentThread().getName() + "\t" + i);
        }
    }

    public void print5() {
        lock.lock();
        try {
            //1.判断
            while (number != 1) {
                c1.await();
            }
            //2.干活
            printNtimes(5);
            //3.通知
            number = 2; //一定要先改标志位
            c2.signal(); //通知c2线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //1.判断
            while (number != 2) {
                c2.await();
            }
            //2.干活
            printNtimes(10);
            //3.通知
            number = 3; //一定要先改标志位
            c3.signal();//通知c3线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //1.判断
            while (number != 3) {
                c3.await();
            }
            //2.干活
            printNtimes(15);
            //3.通知
            number = 1; //一定要先改标志位
            c1.signal();//通知c1线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class juc_06_ConditionDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.print5();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.print10();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                resource.print15();
            }
        }, "CC").start();
    }
}
