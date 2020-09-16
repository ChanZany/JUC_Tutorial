package com.chanzany.leetCode;

/**
 * 小明开了一家糖果店。他别出心裁：把水果糖包成4颗一包和7颗一包的两种。糖果不能拆包卖。
 * 小朋友来买糖的时候，他就用这两种包装来组合。当然有些糖果数目是无法组合出来的，比如要买 10 颗糖。
 * 你可以用计算机测试一下，在这种包装情况下，最大不能买到的数量是17。大于17的任何数字都可以用4和7组合出来。
 * 本题的要求就是在已知两个包装的数量时，求最大不能组合出的数字。
 * 输入：
 * 两个正整数，表示每种包装中糖的颗数(都不多于1000)
 * 要求输出：
 * 一个正整数，表示最大不能买到的糖数
 * 例如：
 * 用户输入：
 * 4 7
 * 程序应该输出：
 * 17
 * 再例如：
 * 用户输入：
 * 3 5
 * 程序应该输出：
 * 7
 */
public class Unable_to_buy {
    public static void main(String[] args) {
//        f(3, 5);
        f2(3, 5);
    }

    public static void f(int a, int b) {
        int N = 1000 * 100;
        int[] flag = new int[N];
        for (int i = 0; i <= N / a; i++) {
            for (int j = 0; j <= N / b; j++) {
                if (a * i + b * j < N) flag[a * i + b * j] = 1;
            }
        }
//        System.out.println(se(flag,a));
        int n = 0;
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == 1) {
                n++;
                if (n >= a) {
                    System.out.println(i - a);
                    return;
                }
            } else {
                n = 0;
            }
        }


    }

    /**
     * 自然数a,b互质,则不能表示成ax+by（x,y为非负整数）的最大整数是ab-a-b.
     */
    public static void f2(int a, int b){
        System.out.println(a*b-a-b);
    }
}
