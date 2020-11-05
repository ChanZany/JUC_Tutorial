package com.chanzany.interview_secondary.juc_07_ThreadPool;
/**
 合理的线程池配置参数
 1. CPU密集型
    CPU密集型任务配置尽可能少的线程数量；
        一般公式：(CPU核数+1)个线程的线程池

 2. IO密集型
    IO密集型时，大部分线程都阻塞，并不是一直在执行任务，故需要多配置线程数：
        参考公式：CPU核数/(1-阻塞系数)     阻塞系数在0.8~0.9之间
        如8核CPU: 8/(1-0.9) = 80个线程数


*/
public class ReasonableParameters {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors()); //CPU核数

    }
}
