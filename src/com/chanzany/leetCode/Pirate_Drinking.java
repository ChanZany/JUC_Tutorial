package com.chanzany.leetCode;

/**
 * 有一群海盗（不多于20人），在船上比拼酒量。过程如下：打开一瓶酒，
 * 所有在场的人平分喝下，有几个人倒下了。再打开一瓶酒平分，又有倒下的，
 * 再次重复…… 直到开了第4瓶酒，坐着的已经所剩无几，海盗船长也在其中。
 * 当第4瓶酒平分喝下后，大家都倒下了。 等船长醒来，发现海盗船搁浅了。
 * 他在航海日志中写到：“……昨天，我正好喝了一瓶…….奉劝大家，开船不喝酒，喝酒别开船……”
 * 请你根据这些信息，推断开始有多少人，每一轮喝下来还剩多少人。
 * 如果有多个可能的答案，请列出所有答案，每个答案占一行。
 * 格式是：人数,人数,…
 * 例如,有一种可能是：20,5,4,2,0
 */
public class Pirate_Drinking {

    public static void main(String[] args) {
        f();
    }

    private static void f() {
        for (int a = 20; a >= 1; a--)
            for (int b = a - 1; b >= 1; b--)
                for (int c = b - 1; c >= 1; c--)
                    for (int d = c - 1; d >= 1; d--) {
//                        if (1.0 / a + 1.0 / b + 1.0 / c + 1.0 / d == 1)
                        if (b*c*d + a*c*d + a*b*d + a*b*c == a*b*c*d)
                            System.out.println(a + "," + b + "," + c + "," + d);
                    }

    }
}