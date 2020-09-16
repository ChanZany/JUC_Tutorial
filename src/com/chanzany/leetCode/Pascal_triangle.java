package com.chanzany.leetCode;

import java.util.Scanner;

/**
 * 杨辉三角
 */
public class Pascal_triangle {

    public static void main(String[] args) {
        int level = new Scanner(System.in).nextInt();
        for (int i = 0; i < level+1; i++) {
            System.out.print(f(level,i)+"  ");
        }
    }

    private static int f(int m, int n) {
        if (m==0) return 1;
        if (n==0||n==m) return 1;
        return f(m-1,n-1)+f(m-1,n);
    }

}
