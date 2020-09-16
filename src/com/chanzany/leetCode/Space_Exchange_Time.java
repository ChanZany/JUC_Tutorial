package com.chanzany.leetCode;


import java.util.Scanner;

public class Space_Exchange_Time {
    public static void main(String[] args) {
        final int num = 10007;
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        if (n == 1 || n == 2) {
            System.out.println(1);
        } else {
            int[] a = new int[n];
            a[0] = 1;
            a[1] = 1;
            for (int i = 2; i < n; i++) {
                a[i] = (a[i - 1] + a[i - 2]) % num;
            }
            System.out.println(a[n-1]);
        }

    }


}
