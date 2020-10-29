package com.chanzany.JUC;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * 异步回调
 */
public class juc_16_CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*无返回值的异步回调
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值 update mysql");
        });
        System.out.println(voidCompletableFuture.get());
        */
        //有返回值的异步回调
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+"有返回值 insert into mysql");
//            int age = 10/0;
            return 1024;
        });
        Integer result = integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("***t:" + t);//null
            System.out.println("***u:" + u);//ArithmeticException: / by zero
        }).exceptionally(f -> {
            System.out.println("***exception:" + f.getMessage());
            return 404;
        }).get();
        System.out.println("***result:"+result);


    }
}
