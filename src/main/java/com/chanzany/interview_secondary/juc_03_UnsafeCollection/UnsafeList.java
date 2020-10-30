package com.chanzany.interview_secondary.juc_03_UnsafeCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合类不安全问题
 */
public class UnsafeList {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        List<Integer> vector = new Vector<>();
        List<Integer> synchronizedList = Collections.synchronizedList(list);
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
//                 list.add(finalI);
//                vector.add(finalI);
//                 synchronizedList.add(finalI);
                copyOnWriteArrayList.add(finalI);
                //        System.out.println(list);//ConcurrentModificationException
//        System.out.println(synchronizedList);
                System.out.println(copyOnWriteArrayList);
            }, String.valueOf(i)).start();
        }


    }

}
