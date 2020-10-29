package com.chanzany.interview_secondary.juc01_volatile;

import java.util.concurrent.atomic.AtomicInteger;

class MyNumber {
    //    int number = 0;
    volatile int number = 0;
    volatile AtomicInteger atomicNum = new AtomicInteger();
    public void add1024() {
        this.number = 1024;
    }

    public void addOne() {
        number++;
    }

    public void addOneAtomic() {
        atomicNum.getAndIncrement();
    }
}


public class jmm_volatile {
    public static void main(String[] args) {
        volatile_atomicity();

    }

    /**
     * 在 Java 中 volatile、synchronized 和 final 实现可见性。
     * 可见性，是指线程之间的可见性，一个线程修改的状态对另一个线程是可见的。
     * 也就是一个线程修改的结果。另一个线程马上就能看到。
     */
    public static void volatile_visibility() {
        MyNumber myNumber = new MyNumber();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "*****come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.add1024();
            System.out.println(Thread.currentThread().getName() + "\t update number to " + myNumber.number);
        }, "AAA").start();

        while (myNumber.number == 0) {
            //需要有一种通知机制告诉main线程，主内存中的number已经被A线程修改为了1024，从而跳出while循环
            //注意：在JMM中线程之间不可访问对方的工作内容，所以线程间通信必须通过主内存来实现(实现方式volatile)
        }
        System.out.println(Thread.currentThread().getName() + "\t the visibility is proved");
    }

    /**
     * volatile是乞丐版的synchronized,只支持可见性不支持原子性，
     * 所以在多线程环境下会产生数据安全问题
     *
     * why? JMM内存模型中三个线程几乎同时向主内存中写入修改后的数据，后面的会把前面的覆盖掉
     *
     * 如何解决？
     * -- 方法中加synchronized
     * -- 使用lock
     * -- 使用原子包装类
     */
    public static void volatile_atomicity() {
        MyNumber myNumber = new MyNumber();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myNumber.addOne();
                    myNumber.addOneAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //需要等待上面20个线程全部计算完后，再使用main线程获取最终的结果
        while (Thread.activeCount()>2){//一个main线程，一个GC线程
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t finally number value:"+myNumber.number);
        System.out.println(Thread.currentThread().getName()+"\t finally number value:"+myNumber.atomicNum);
    }


}
