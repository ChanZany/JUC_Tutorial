package com.chanzany.leetCode;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = new Scanner(System.in).next();
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (c<=90)
                c += 32;
            else c-= 32;
            sb.append(c);
        }
        System.out.println(sb.toString());
    }
}
