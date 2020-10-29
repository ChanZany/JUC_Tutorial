package com.chanzany.JUC;

/**
 * 由于String的不可变性导致，字符串变更时效率低下，在之后得JDK版本中出现了StringBuilder和StringBuffer 。
 * 类可变性线程安全String不可变安全StringBuffer可变安全StringBuilder可变非安全
 * 使用选择
 * 1、当有少量连接操作时，使用String
 * 2、当单线程下有大量连接操作时，使用StringBuilder
 * 3、当多线程下有大量连接操作时，使用StringBuffer
 */
public class juc_String_constant_poor {
    public static void main(String[] args) {
        String str1 = "HelloFlyapi";//此方式在方法区中字符串常量池中创建对象
        String str2 = "HelloFlyapi";
        String str3 = new String("HelloFlyapi"); //此方式在堆内存创建对象
        String str4 = "Hello";
        String str5 = "Flyapi";
        //由于”Hello”和”Flyapi”都是常量，编译时，这句代码会被自动编译为‘String str6 = “HelloFlyapi”;
        String str6 = "Hello" + "Flyapi";
        //JVM会在堆（heap）中创建一个以str4为基础的一个StringBuilder对象，
        // 然后调用StringBuilder的append()方法完成与str5的合并，
        // 之后会调用toString()方法在堆（heap）中创建一个String对象，并把这个String对象的引用赋给str7。
        String str7 = str4 + str5;

        System.out.println("str1 == str2 result: " + (str1 == str2));//True
        System.out.println("str1 == str3 result: " + (str1 == str3));//false
        System.out.println("str1 == str6 result: " + (str1 == str6));//true
        System.out.println("str1 == str7 result: " + (str1 == str7));//false
        // public native String intern() 返回字符串对象的规范化表示形式。
        // String.intern()是一个Native方法，底层调用C++的 StringTable::intern方法实现。
        // 当通过语句str.intern()调用intern()方法后，
        // JVM 就会在当前类的常量池中查找是否存在与str等值的String，
        // 若存在则直接返回常量池中相应Strnig的引用；
        // 若不存在，则会在常量池中创建一个等值的String，然后返回这个String在常量池中的引用。
        // 因此，只要是等值的String对象，使用intern()方法返回的都是常量池中同一个String引用，
        // 所以，这些等值的String对象通过intern()后使用==是可以匹配的。
        System.out.println("str1 == str7.intern() result: " + (str1 == str7.intern()));//true
        System.out.println("str3 == str3.intern() result: " + (str3 == str3.intern()));//false
        System.out.println("str3 == str3.intern() result: " + (str3.intern() == str3.intern()));//true
    }
}
