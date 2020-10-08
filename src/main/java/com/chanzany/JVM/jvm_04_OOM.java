package com.chanzany.JVM;

import java.util.Random;

public class jvm_04_OOM {
    public static void main(String[] args) {
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        String str = "oom...";
        while (true){
            str += str+new Random().nextInt(888888888)+new Random().nextInt(999999999);
        }
//        byte[] bytes = new byte[1024*1024*40];
    }
}
