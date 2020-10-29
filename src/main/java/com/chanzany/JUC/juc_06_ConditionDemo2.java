package com.chanzany.JUC;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程，一个线程打印1-52，另一个打印字母A-Z打印顺序为12A34B...5152Z,
 */
class Resource2 {
    private int num = 1;
    private char c = 'A';
    private int counter = 0;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();

    public void printNum() {
        lock.lock();
        try {
            //判断
            while (counter == 2) {
                c1.await();
            }
            //干活
            for (int i = 0; i < 2; i++) {
                System.out.println(num++);
            }
            //通知
            counter = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void printChar() {
        lock.lock();
        try {
            while (counter==0){
                c2.await();
            }
            System.out.println(c++);
            counter=0;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

public class juc_06_ConditionDemo2 {
    public static void main(String[] args) {
        Resource2 resource = new Resource2();
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                resource.printNum();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                resource.printChar();
            }
        }, "B").start();
    }
}
