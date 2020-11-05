package com.chanzany.interview_secondary.juc_08_Deadlock;


import java.util.concurrent.TimeUnit;

/**
 * 什么是死锁：
 * 两个线程都在等对方先释放锁然后占有，好比电影里面的： A:你先把刀放下 B:你先把枪放下
 * <p>
 * 产生死锁的主要原因
 * 系统资源不足
 * 进程运行推进的顺序不合适
 * 资源分配不当
 * 解决
 * jps命令定位进程编号 jps -l --> 进程ID
 * jstack找到死锁查看  jstack 进程ID -->Found one Java-level deadlock:
 */

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t持有:"+lockA
            +"\t尝试获取："+lockB);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace();}
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t持有:"+lockB
                +"\t尝试获取："+lockA);
            }

        }

    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "LockA";
        String lockB = "LockB";
        new Thread(new HoldLockThread(lockA,lockB),"Thead 1").start();
        new Thread(new HoldLockThread(lockB,lockA),"Thead 2").start();

        /*
        * linux ps -ef|grep xxxx    ls -l
        * windows下的java运行程序，也有类似ps的查看进程(java)命令
        *       jps = java ps       jps -l
        * */
    }
}
