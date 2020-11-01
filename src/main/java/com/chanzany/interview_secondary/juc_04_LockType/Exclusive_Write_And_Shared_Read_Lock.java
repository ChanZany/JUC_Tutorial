package com.chanzany.interview_secondary.juc_04_LockType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁-写锁
 * 指该锁一次只能被一个线程持有，对ReentrantLock和Synchronized而言都是独占锁
 * 共享锁-读锁
 * 指该锁可被多个线程持有
 * 对ReentrantReadWriteLock 其读锁是共享锁，写锁是独占锁
 * 读锁的共享可保证高效的并发读，读写，写读，写写的过程是互斥的。
 */

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public synchronized void put(String key, Object value) {
        lock.writeLock().lock();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
        System.out.println(Thread.currentThread().getName() + "\t正在写入:" + key);
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t写入完成:" + key);

    }

    public Object get(String key) {
        lock.readLock().lock();
        Object value = null;
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取:");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成:" + value);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
        return value;
    }

    public void clean() {
        map.clear();
    }
}

public class Exclusive_Write_And_Shared_Read_Lock {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.put(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace();}
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.get(finalI + "");
            }, String.valueOf(i)).start();
        }
    }

}
