package com.chanzany.interview_secondary.juc02_CAS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
@Data
@ToString
class User {
    private String userName;
    private int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zs = new User("zhangsan", 22);
        User ls = new User("lisi", 21);
        User ww = new User("wangwu", 41);
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(zs);
        System.out.println(userAtomicReference.compareAndSet(zs, ls) + "\t" +
                userAtomicReference.get().toString());
        System.out.println(userAtomicReference.compareAndSet(ww, zs) + "\t" +
                userAtomicReference.get().toString());

    }
}
