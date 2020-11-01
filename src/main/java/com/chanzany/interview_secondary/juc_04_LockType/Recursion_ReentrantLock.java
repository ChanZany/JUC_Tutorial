package com.chanzany.interview_secondary.juc_04_LockType;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁（也叫递归锁）--synchronized/ReentrantLock
 * <p>
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 * <p>
 * 也即，线程可以进入任何一个它已经拥有了的锁同步代码块
 * <p>
 * 可重入锁的最大作用是防止死锁
 */

class Phone implements Runnable {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t 发短信");
        sendEMail();
    }

    public synchronized void sendEMail() {
        System.out.println(Thread.currentThread().getName() + "\t 发邮件");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        open_QQ();
    }

    public void open_QQ() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"打开QQ");
            open_WeChat();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void open_WeChat() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"打开微信");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class Recursion_ReentrantLock {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(phone::sendSMS, "t1").start();
        new Thread(phone::sendSMS, "t2").start();

        new Thread(phone, "t3").start();
        new Thread(phone, "t4").start();
    }
}
