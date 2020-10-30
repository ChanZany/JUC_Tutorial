package com.chanzany.interview_secondary.juc02_CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题:狸猫换太子
 * <p>
 * 如何解决：
 * 理解原子引用+新增版本控制机制（类似时间戳）
 * T1   [100 1]             [2020 2] --> 最终结果[100 3]
 * T2   [100 1]   [102 2]   [100 3]
 * <p>
 * 为此，JUC提供了AtomicStampedReference
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    public static void main(String[] args) {

        System.out.println("=============以下是ABA问题的产生=============");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            //暂停线程1秒钟，确保上面的t1线程完成了一次ABA操作
            try { TimeUnit.SECONDS.sleep(1); } catch (Exception e) { e.printStackTrace(); }
            System.out.println(atomicReference.compareAndSet(100, 2019)+"\t"+
                    atomicReference.get());
        }, "t2").start();

        try { TimeUnit.SECONDS.sleep(2); } catch (Exception e) { e.printStackTrace(); }

        System.out.println("=============以下是ABA问题的解决=============");
        AtomicStampedReference<Integer> atomicStamped = new AtomicStampedReference<>(100, 1);
        new Thread(()->{
            int stamp = atomicStamped.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号:"+stamp);
            try { TimeUnit.SECONDS.sleep(1); } catch (Exception e) { e.printStackTrace(); }
            //ABA操作
            atomicStamped.compareAndSet(100,101,atomicStamped.getStamp(),atomicStamped.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第2次版本号:"+atomicStamped.getStamp());
            atomicStamped.compareAndSet(101,100,atomicStamped.getStamp(),atomicStamped.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第3次版本号:"+atomicStamped.getStamp());
        },"t3").start();
        new Thread(()->{
            int stamp = atomicStamped.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号:"+stamp);
            try { TimeUnit.SECONDS.sleep(3); } catch (Exception e) { e.printStackTrace(); }
            System.out.println("是否修改成功:"+atomicStamped.compareAndSet(100, 2020,stamp,stamp+1));
            System.out.println("当前最新实际版本号:"+atomicStamped.getStamp());
            System.out.println("当前实际最新值："+atomicStamped.getReference());

        },"t4").start();
    }
}
