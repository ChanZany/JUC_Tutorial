package com.chanzany.JUC;


class AirCondition {
    private int number = 0;

    public synchronized void increment() throws Exception {
        //1. 判断
        if (number != 0) {
            this.wait();
        }
        //2. 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3. 通知
        this.notifyAll();

    }

    public synchronized void decrement() throws Exception {
        if(number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }


}

/**
 * 题目:两个线程，可以操作初始值为0的一个变量
 * 实现一个线程对该变量+1.一个线程对该变量-1，
 * 实现交替，来10轮，变量初始值为0
 * <p>
 * 1. 高内聚，低耦合前提下，线程操作资源类
 * 2. 判断/干活/通知
 */
public class juc_05_ProdConsumer {

    public static void main(String[] args) {
        AirCondition resource = new AirCondition();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    resource.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "producer").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    resource.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
        
    }
}
