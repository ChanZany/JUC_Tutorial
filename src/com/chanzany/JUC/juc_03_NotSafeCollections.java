package com.chanzany.JUC;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * *
 * 集合类不安全
 * 1. 故障现象
 * java.util.ConcurrentModificationException
 * 2. 导致原因
 * public boolean add(E var1)
 * 3. 解决方案
 * 3.1 Vector-->
 * public synchronized boolean add(E var1) 粗粒度加锁(对整个方法加锁)
 * 3.2 Collections.synchronizedList(new ArrayList<>()) -->
 * public void add(int var1, E var2) {
 * synchronized(this.mutex) { 中等粒度加锁(对方法体中的逻辑运算加锁)
 * this.list.add(var1, var2);
 * }
 * }
 * 3.3 CopyOnWriteArrayList-->
 * public boolean add(E var1) {
 * ReentrantLock var2 = this.lock; **细粒度加锁**(使用可重用锁对逻辑运算加锁)
 * var2.lock();
 * 4. 优化建议
 */
public class juc_03_NotSafeCollections {
    public static void main(String[] args) {

//        Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),
                        UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }

    public static void setnotSafe() {
//        Set<String> set = new HashSet<>();
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    public static void listNotSafe() {
        //        List<String> list = new ArrayList<>();
        //        List<String> list = new Vector<>();
        //        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
