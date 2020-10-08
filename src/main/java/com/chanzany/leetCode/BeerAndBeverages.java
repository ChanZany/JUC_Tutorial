package com.chanzany.leetCode;

/**
 * 啤酒每罐2.3，饮料每罐1.9.小明买了若干啤酒饮料，一共花了82.3
 * 已知啤酒比饮料少，请计算买了几罐啤酒
 */
public class BeerAndBeverages {

    public static void main(String[] args) {
        f();
    }

    private static void f() {
        /*
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < i; j++) {
                if (j*2.3+i*1.9==82.3){
                    System.out.println("bear:"+j+"  Beverages:"+i);
                }
            }
        }*/
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < i; j++) {
//                if (j*23+i*19==823){ // 改进1：小数转整数
                if (Math.abs(j * 2.3 + i * 1.9 - 82.3) < 1e-10) { // 改进2：用差值无限小替换相等
                    System.out.println("bear:" + j + "  Beverages:" + i);
                }
            }

        }

    }
}
