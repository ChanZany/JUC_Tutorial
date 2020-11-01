package com.chanzany.interview_secondary.juc_04_LockType;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class WhatReentrant2 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("第1次获取锁，这个锁是：" + lock);

                int index = 1;
                while (true) {
                    try {
                        lock.lock();
                        System.out.println("第" + (++index) + "次获取锁，这个锁是：" + lock);
                        try {
                            Thread.sleep(new Random().nextInt(200));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (index == 10) {
                            break;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
