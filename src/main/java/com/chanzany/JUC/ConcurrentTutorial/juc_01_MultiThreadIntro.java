package com.chanzany.JUC.ConcurrentTutorial;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：3个售票员 卖出 30张票
 * 如何编写企业级的多线程代码
 * 固定的编程套路+模板是什么？
 * <p>
 * 1. 在高内聚低耦合的前提下，线程   操作  资源类
 * 1.1. 先创建资源类(高内聚功能并向外部提供操作接口)
 */

class Ticket { //资源类=实例变量+实例方法
    private int number = 30;

    Lock lock = new ReentrantLock();

    //功能内聚
    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() +
                        "\t卖出第：" + (number--) +
                        "张票\t还剩下" +
                        number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}

public class juc_01_MultiThreadIntro {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();

//        Thread t1 = new Thread();
        /**
         * 线程状态：
         * NEW,RUNNABLE,BLOCKED,
         * WAITING,TIMED_WAITING,TERMINATED;
         */
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 400; i++) {
                    ticket.sale();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 400; i++) {
                    ticket.sale();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 400; i++) {
                    ticket.sale();
                }
            }
        },"C").start();*/

        new Thread(()->{ for (int i = 0; i < 400; i++) ticket.sale(); }, "A").start();
        new Thread(()->{ for (int i = 0; i < 400; i++) ticket.sale(); }, "B").start();
        new Thread(()->{ for (int i = 0; i < 400; i++) ticket.sale(); }, "C").start();


    }

}



