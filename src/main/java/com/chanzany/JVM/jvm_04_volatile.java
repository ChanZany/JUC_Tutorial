package com.chanzany.JVM;

class MyNumber {
//    int number = 10;
    volatile int number = 10;

    public void add1024() {
        this.number = 1024;
    }
}

/**
 * 在 Java 中 volatile、synchronized 和 final 实现可见性。
 * 可见性，是指线程之间的可见性，一个线程修改的状态对另一个线程是可见的。
 * 也就是一个线程修改的结果。另一个线程马上就能看到。
 *
 */
public class jvm_04_volatile {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "*****come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.add1024();
            System.out.println(Thread.currentThread().getName()+"\t update number to "+myNumber.number);
        }, "AAA").start();

        while (myNumber.number==10){
            //需要有一种通知机制告诉main线程，主内存中的number已经被A线程修改为了1024，从而跳出while循环
            //注意：在JMM中线程之间不可访问对方的工作内容，所以线程间通信必须通过主内存来实现(实现方式volatile)
        }
        System.out.println(Thread.currentThread().getName()+"\t the visibility is proved");

    }
}
