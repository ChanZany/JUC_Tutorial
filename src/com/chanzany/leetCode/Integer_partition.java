package com.chanzany.leetCode;

import java.util.Arrays;

/**
 * 整数划分问题。
 * 如，6=5+1，3+3，4+2，4+1+1，3+2+1，3+1+1+1........
 * 思路：
 * a[0]=5,a[1]=1
 * a[0]=4,a[1]=2
 *          a[2]=1
 *          a[3]=1
 * a[0]=3,a[1]=3
 *          a[2]=2
 *          a[3]=1
 * a[0]=2,a[1]=4
 */
public class Integer_partition {
    public static void main(String[] args) {
        f(6,new int[100],0);
    }

    /**
     * 对n做加法划分
     * @param n 被划分的整数
     * @param a 缓存
     * @param k 当前位置
     */
    private static void f(int n,int[]a,int k) {
        if (n<=0){
            for (int i = 0; i < k; i++) {
                System.out.print(a[i]+" ");
            }
            System.out.println();
            return;
        }
        for (int i = n; i >0 ; i--) { // i=4,2
            if (k>0 && a[k-1]<i) continue;
            a[k]=i;             //a[0]=4,a[1]=2
            f(n-i,a,k+1); // f(6-4,a,1),f(2-2,a,2)
        }


    }
}
