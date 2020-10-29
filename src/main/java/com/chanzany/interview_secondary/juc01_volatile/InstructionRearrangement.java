package com.chanzany.interview_secondary.juc01_volatile;

/**
 * volatile实现了禁止指令重排优化，从而避免了多线程环境下程序出现乱序执行的现象
 */
public class InstructionRearrangement {
    int a = 0;
    boolean flag = false;

    public void method1() {
        a = 1;
        flag = true;
    }

    //多线程环境中指令交替执行，由于编译器优化重排的存在，
    // 两个线程中使用的变量是否能够保证一致性是无法确定的，结果无法预测
    public void method2() {
        if (flag) {
            a = a + 5;
        }
    }
}
class Main{
    public static void main(String[] args) {
        InstructionRearrangement inst = new InstructionRearrangement();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                for (int j = 0; j < 200; j++) {
                    inst.method1();
                    inst.method2();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(inst.a);
    }
}
