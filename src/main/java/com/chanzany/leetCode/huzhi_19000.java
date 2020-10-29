package com.chanzany.leetCode;

public class huzhi_19000 {
    //输入x，输出x与19000的公约数数量是否大于1
    /*private static boolean f(int x){
        int counter = 0;
        if (x<=1){return false;}
        for (int i = 1; i <= x; i++) {
            if (19000%i ==0 && x%i==0){
                counter+=1;
            }
            if (counter>1){
                return true;
            }
        }

        return false;
    }*/

    //求最大公约数[a,b]-->[b%a,a]-->[0,result]
    public static int f(int a,int b){
        if (a==0) return b;
        return f(b%a,a);
    }

    public static void main(String[] args) {
        /*int counter = 0;
        for (int i = 1; i <= 19000; i++) {
            if (!f(i)){
                counter++;
            }
        }
        System.out.println(counter);*/
        System.out.println(f(9, 6));
    } 
}
