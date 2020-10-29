package com.chanzany.interview_primary;

/**
 * i++和++i命令的区别：
 *
 * 1、i++是先使用i，再赋值计算，即就是在计算程序时，先把i的值拿来用,然后再自增1。
 *
 * 2、++i是先赋值计算，再使用，即就是在计算程序时，是想把 i 自增1然后拿来用。
 *
 * 3、 ++i，在字节码层面，会先进行iinc，也就是执行自增，然后load变量。
 *
 * 4、 i++,则是，先load变量，后自增(因为已经load，所以本次自增，并不会影响已经load的变量值)。
 */
public class SelfIncreasingTest {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i="+i);
        System.out.println("j="+j);
        System.out.println("k="+k);
    }
}
