package com.chanzany.leetCode;

/**
 * 计算m个A,n个B可以组成多少种排列
 * 如 AAABB,AABBA ....
 * 3A 2B
 * -->A+2A2B
 *      -->A+1A2B
 *      -->B+2A1B
 * -->B+3A1B
 *      -->A+2A1B
 *      -->B+3A(4种)
 *
 */
public class A_B_Permutation {

    private static int f(int m,int n){
       if (m==1) return n+1;
       if (n==1) return m+1;
       return f(m-1,n)+f(m,n-1);
    }

    public static void main(String[] args) {
        System.out.println(f(3,2));
    }
}
