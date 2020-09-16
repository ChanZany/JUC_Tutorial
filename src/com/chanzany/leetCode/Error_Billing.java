package com.chanzany.leetCode;

/**
 * 已知有错的账单总数为sum=6
 * 给出的子账单为a[]={1,2,3,3,5}
 * 求可能漏掉的账单
 */
public class Error_Billing {
    /**
     *
     * @param errorSum 有错的和
     * @param a 明细(子账单)
     * @param k 当前处理的位置
     * @param currentSum 前面的元素累加和
     * @param b 记录a的对应项是否选取
     */
    private static void f(int errorSum,int[]a,int k,int currentSum,boolean[] b){
        if (currentSum>errorSum) return;
        if (errorSum == currentSum){
            for (int i = 0; i < b.length; i++) {
                if (!b[i]) System.out.print(i+" ");
            }
            System.out.println();
            return;
        }
        if (k >= a.length) return;

        //不选中第k个明细
        b[k]=false;
        f(errorSum,a,k+1,currentSum,b);

        //选中第k个明细
        b[k]=true;
        currentSum += a[k];
        f(errorSum,a,k+1,currentSum,b);
        //TODO 回溯！！！
        b[k]=false;
    }

    public static void main(String[] args) {
        int sum = 6;
        int[] a = {3,2,4,3,1};
        boolean[] b = new boolean[a.length];//表示a的对应项是否选取
        f(sum,a,0,0,b);
    }
}
