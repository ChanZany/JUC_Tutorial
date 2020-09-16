package com.chanzany.JUC;

/**
 * 1. 函数式编程
 * 拷贝小括号，写死右箭头，落地大括号
 * 2. @FunctionalInterface
 * 函数式接口有且仅有一个未实现方法
 * 3. default method
 * jdk1.8后可以在接口中定义多个默认实现的方法，而且与函数式接口不冲突
 * 4. static method(同default method)
 */

@FunctionalInterface
interface Foo {
    public int add(int x, int y);
//    public void sayHello();
    public default int mul(int x,int y){
        return x*y;
    }
    public default int mul2(int x,int y){
        return x*y;
    }

    public static int div(int x,int y){
        return x/y;
    }
    public static int div2(int x,int y){
        return x/y;
    }

}

public class juc_02_LambdaExpression {
    public static void main(String[] args) {
        /*Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello world");
            }
        };*/
        Foo foo = (int x, int y) -> x + y;
        System.out.println(foo.add(1, 2));
        System.out.println(foo.mul(3,5));
        System.out.println(Foo.div(10,2));

    }

}
