package com.chanzany.interview_secondary.juc_04_LockType;

import java.util.concurrent.locks.ReentrantLock;

/**例子：
 * Creates an instance of {@code ReentrantLock} with the
 * given fairness policy.
 *
 * @param "fair" {@code true} if this lock should use a fair ordering policy

public ReentrantLock(boolean fair) {
        sync = fair ? new FairSync() : new NonfairSync();
        }
公平锁
是指多个线程按照申请锁的顺序来获取锁类似排队打饭 先来后到
非公平锁
是指在多线程获取锁的顺序并不是按照申请锁的顺序,有可能后申请的线程比先申请的线程优先获取到锁,在高并发的情况下,有可能造成优先级反转或者饥饿现象

Java ReentrantLock而言,
通过构造函数指定该锁是否是公平锁 默认是非公平锁 非公平锁的优点在于吞吐量比公平锁大.
对于synchronized而言 也是一种非公平锁.
 */
public class Fair_NonFair_Lock {
    public static void main(String[] args) {
        new ReentrantLock(true);
    }
}
