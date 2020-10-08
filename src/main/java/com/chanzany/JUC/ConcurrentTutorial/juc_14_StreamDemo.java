package com.chanzany.JUC.ConcurrentTutorial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
class User {
    private Integer id;
    private String userName;
    private int age;
}

/**
 * 请按照给出的数据，找出同时满足以下条件的用户
 * 偶数ID 且 年龄大于24 且 用户名转为大写 且 用户名字母倒排序 只输出一个用户名字
 */
public class juc_14_StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream().filter(user -> user.getId() % 2 == 0 && user.getAge() > 24)
                .map(user -> user.getUserName().toUpperCase())
                .sorted(Comparator.reverseOrder()).limit(1).forEach(System.out::println);


        //***************************************************************
        /*四大函数式接口
        //函数型接口 R apply(T t)
        Function<String, Integer> function = s -> s.length();
        System.out.println(function.apply("abc"));
        //断定型接口 Boolean test(T t)
        Predicate<Integer> predicate = integer -> integer % 2 == 0;
        System.out.println(predicate.test(13));
        //消费型接口 void accept(T t)
        Consumer<User> consumer= user->user.setUserName(user.getUserName().toUpperCase());
        consumer.accept(u1);
        System.out.println(u1);
        //供给型接口 T get()
        Supplier<String> supplier = () -> Runtime.getRuntime().totalMemory()/(double)1024/1024+"MB";
        System.out.println(supplier.get());
        */

    }
}
