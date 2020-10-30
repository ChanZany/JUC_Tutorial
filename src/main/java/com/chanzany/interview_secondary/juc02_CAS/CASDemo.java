package com.chanzany.interview_secondary.juc02_CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. CAS是什么？
 * 比较并交换(Compare And Swap/Set)
 *  -- 比较当前工作内存中的值和主内存中的值，若相同则执行规定操作，否则更新当前工作内存中的值
 *  直到主内存和工作内存中的值一致为止。
 *
 * 2. CAS的底层原理
 * 自旋锁，Unsafe类
 * public final int getAndAddInt(Object atomicInteger, long valueOffset, int var4) {
 * int var5;
 * do {
 * var5 = this.getIntVolatile(atomicInteger, valueOffset); //根据内存地址拿到对应的值
 * } while(!this.compareAndSwapInt(atomicInteger, valueOffset, var5, var5 + var4));//比较并交换（native）
 * // 如果相同，更新var5 + var4并且返回true，退出while循环
 * // 如果不同，继续取值然后再比较。直到更新完成。
 * return var5;
 * }
 *
 * 3. CAS的缺点
 * 3.1 如果CAS失败，该线程会一直尝试。如果CAS长时间不成功，可能会给CPU带来很大的开销
 * 【没有使用锁原语，保证了并发性，但是存在CPU开销隐患】
 * 3.2 只能保证一个共享变量的原子操作
 * 3.3 引出来ABA问题！！！
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        /**
         * Atomically sets the value to the given updated value
         * if the current value {@code ==} the expected value.
         *
         * 如果当前原子值满足了expect_value，则将之改为update_value
         * 否则不做改动
         *
         * @param expect the expected value
         * @param update the new value
         * @return {@code true} if successful. False return indicates that
         * the actual value was not equal to the expected value.
         */
        System.out.println(atomicInteger.compareAndSet(5, 6));
        System.out.println(atomicInteger.compareAndSet(5, 7));
        System.out.println(atomicInteger.compareAndSet(6, 66));
        System.out.println(atomicInteger.get());

    }
}
