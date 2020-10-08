package com.chanzany.JUC.ConcurrentTutorial;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/*class MyThread implements Runnable{
    @Override
    public void run() {

    }
}*/

/**
 * 实现多线程的方法：
 * 1. 继承Thread类
 * 2. 实现Runnable接口，重写run方法（无返回值）
 * 3. 实现Callable接口，重写call方法（有返回值）
 *      注意： get 方法一般请放在最后一行，调用时才执行对应的call方法，这样可以让耗时的计算放在最后
 * 4. 通过线程池
 */
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" come in callable1");
        //模拟一个大型计算任务
        TimeUnit.SECONDS.sleep(4);
        return 1024;
    }
}

public class juc_07_CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName()+"  come in callable2");
            TimeUnit.SECONDS.sleep(4);
            return 2048;
        });

        new Thread(futureTask,"A").start();
        new Thread(futureTask2,"B").start();

        System.out.println(Thread.currentThread().getName()+" come over");

        while(!futureTask.isDone()){
            System.out.println("***wait");
        }

        System.out.println(futureTask.get());

    }
}

/**
 *
 在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些作业交给Future对象在后台完成，
 当主线程将来需要时，就可以通过Future对象获得后台作业的计算结果或者执行状态。

 一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。

 仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法。一旦计算完成，
 就不能再重新开始或取消计算。get方法而获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态，
 然后会返回结果或者抛出异常。

 只计算一次
 get方法放到最后
 */