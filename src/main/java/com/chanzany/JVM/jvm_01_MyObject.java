package com.chanzany.JVM;


public class jvm_01_MyObject {
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader());//null
//        System.out.println(object.getClass().getClassLoader().getParent());
//        System.out.println(object.getClass().getClassLoader().getParent().getParent());
        System.out.println("-----------------------------");
        jvm_01_MyObject myobj = new jvm_01_MyObject();
        System.out.println(myobj.getClass().getClassLoader());//AppClassLoader
        System.out.println(myobj.getClass().getClassLoader().getParent());//ExtClassLoader
        System.out.println(myobj.getClass().getClassLoader().getParent().getParent());//null

    }
}
