package com.chanzany.interview_secondary.juc_04_LockType;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁，是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁，这样的好处是减少线程上下文切换的消耗
 * 缺点是会消耗CPU
 *
 * 题目：实现一个自旋锁
 * 自旋锁好处：循环比较获取直到成功为止，没有类似wait的阻塞。
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法使自己持有锁5秒钟，B随后进来发现当前有线程持有锁，
 * 不是null,所以只能通过自旋等待，直到A释放锁后B随后抢到。
 */

public class SpinLock {
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t invoked myLock()");
        while (!atomicReference.compareAndSet(null,thread)){
            System.out.println(Thread.currentThread().getName()+"尝试获取锁..");
        }
        System.out.println(Thread.currentThread().getName()+"获取到锁");
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoked myUnlock()");
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        new Thread(()->{
                   spinLock.myLock();
                   try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace();}
                   spinLock.myUnlock();
                }, "A").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(()->{
            spinLock.myLock();
            spinLock.myUnlock();
        }, "B").start();
    }




}
