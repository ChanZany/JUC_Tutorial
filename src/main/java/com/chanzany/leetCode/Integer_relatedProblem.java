package com.chanzany.leetCode;

/**
 * 与整数相关的问题
 * 如素数、整除、余数
 * 最大公约数，最小公倍数
 */
public class Integer_relatedProblem {

    public static void main(String[] args) {
        /*
        int a = 33;
        int b = 66;
        Greatest_common_divisor(a, b);
        Least_common_multiple(a, b);
        */
//        Euclid_Algorithm(45,10);
//        System.out.println(gcd(45,10));
        System.out.println(f(3, 50, 5));
    }

    //a 的n次幂对P取模
    // a%p +b%p =(a+b)%p
    // (a%p)*(b%p) %p  =(a*b)%p
    public static int f(int a,int n,int p){
        int count = 1;
        for (int i = 0; i < n; i++) {
            count = count*a %p; //每一步都取模后运算，避免数值过限
        }
        return count;
    }

    /**辗转相除法
     a = ka * i +a1
     b = kb * i +b1
     (a+b) %i=((ka+kb)*i + (a1+b1))%i = (a1+b1)%i
     假设 a = ka*i,b=kb*i，则(b-a)=(ka-kb)*i -->(b-a)%i==0
     所以[a,b] --> [b%a,a]
     [15,40]-->[10,15]-->[5,10]-->[0,5]
     可以使用递归！如gcd方法
     */
    public static void Euclid_Algorithm(int a, int b) {
        for (; ; ) {
            if (a==0){
                System.out.println(b);
                break;
            }
            int tmp = a;
            a = b % a;
            b = tmp;

        }

    }

    public static int gcd(int a, int b){
        if (a==0) return b;
        return gcd(a%b,a);
    }

    public static void Least_common_multiple(int a, int b) {
        for (int i = a; i <= a * b; i++) {
            if (i % a == 0 && i % b == 0) {
                System.out.println("最小公倍数:" + i);
                break;
            }
        }
    }

    public static void Greatest_common_divisor(int a, int b) {
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        for (int i = a; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                System.out.println("最大公约数:" + i);
                break;
            }
        }
    }
}
