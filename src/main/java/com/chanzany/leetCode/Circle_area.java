package com.chanzany.leetCode;


import java.util.Scanner;
import java.math.BigDecimal;

/**
 * 要求计算圆的面积，取7位小数
 * 思路：使用BigDecimal.setScale进行控制
 */
public class Circle_area {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int r = input.nextInt();
        double res = Math.PI*r*r;
        BigDecimal decimal = new BigDecimal(res);
        BigDecimal result = decimal.setScale(7,BigDecimal.ROUND_HALF_UP);
        System.out.println(result);
    }
}
