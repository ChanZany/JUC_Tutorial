package com.chanzany.interview_primary;

/**
 * 父类的初始化<clinit>
 * <clinit>()方法由静态类变量显示赋值代码和静态代码块组成
 * 类变量显示赋值代码和静态代码块代码从上到下【顺序执行】
 * <clinit>()方法只执行一次
 *     (1) j = method
 *     (2) 静态代码块
 *
 * 实例初始化<init>()
 * <init>()方法由非静态实例变量显示赋值代码和非静态代码块、对应构造器代码组成
 * 非静态实例变量显示赋值代码和非静态代码块代码从上到下【顺序执行】，而对应构造器的代码最后执行
 *  (1) super()（最前）
 *  (2) i = test()
 *  (3) 父类的非静态代码块
 *  (4) 父类的构造方法(最后)
 *
 * 非静态方法前面其实有一个默认的对象this
 * this在构造器中表示的是正在创建的对象，因为这里是在创建Son对象，所以test()执行的是子类重写的代码(面向对象多态现象)
 */
class Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(1)");
    }

    Father() {
        System.out.print("(2)");
    }

    {
        System.out.print("(3)");
    }


    public int test() {
        System.out.print("(4)");
        return 1;
    }

    public static int method() {
        System.out.print("(5)");
        return 1;
    }
}

/**
 * 子类的初始化<clinit>:
 *   先初始化父类 5 1
 *   再初始化子类 10 6
 * 子类的实例化<init>
 *     (1) super()（最前） 9  3  2
 *     (2) i = test()     9
 *     (3) 子类的非静态代码块  8
 *     (4) 子类的构造方法(最后) 7
 *
 * 因为创建了两个Son对象，因此实例化方法<init>执行两次
 */
class Son extends Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(6)");
    }

    Son() {
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }
    @Override
    public int test() {
        System.out.print("(9)");
        return 1;
    }

    public static int method() {
        System.out.print("(10)");
        return 1;
    }

}


public class Class_Init_Order {
    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}