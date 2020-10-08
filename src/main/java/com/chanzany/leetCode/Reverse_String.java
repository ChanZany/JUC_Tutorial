package com.chanzany.leetCode;

/**
 * 字符串反转的递归实现
 * 1. 找到相似处
 * 2. 定义出口
 */
public class Reverse_String {

    private static String f(String s){
        if (s.length()==1){
            return s;
        }
        return s.substring(1)+s.charAt(0);
    }

    public static void main(String[] args) {
        System.out.println(f("abcd"));
    }
}
