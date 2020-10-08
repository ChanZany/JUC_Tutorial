package com.chanzany.leetCode;



/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），求其最大和。
 * 从第一个元素开始，sum=nums[0]+nums[1]+...
 * 一旦sum变成负数，说明往后累加元素都不如后面的元素本身的值大，所以序列断开，从当前位置重新计算sum
 * 每次得到的sum都与max对比保证max记录最大的sum.
 * 算法：可解、优化
 */
public class Largest_subsequence_sum {

    public static void main(String[] args) {

        int[] nums={-2,  1,  -3,  4,  -1,  2,  1,  -5,  4};

        int k = f(nums);
        System.out.println(k);
    }

    private static int f(int[] nums) {
        int max = nums[0];
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (sum>0){
                sum += +nums[i];
            }else {
                sum = nums[i];
            }
            if (sum>max) max=sum;
        }

        return max;
    }


}
