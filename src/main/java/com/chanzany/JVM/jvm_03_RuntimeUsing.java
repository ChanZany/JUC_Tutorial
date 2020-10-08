package com.chanzany.JVM;

public class jvm_03_RuntimeUsing {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors()); //cpu的核数
        long maxMemory = Runtime.getRuntime().maxMemory() ;//返回 Java 虚拟机试图使用的最大内存量。
        long totalMemory = Runtime.getRuntime().totalMemory() ;//返回 Java 虚拟机中的初始内存总量。
        //实际生产环境中，初始内存总量需要配置为最大内存量，避免内存的使用忽高忽低。
        System.out.println("-Xmx:MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("-Xms:TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");

    }
}
