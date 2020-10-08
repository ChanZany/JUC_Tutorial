package com.chanzany.JUC.ConcurrentTutorial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Book {
    //链式编程+流式计算
    private int id;
    private String bookName;
    private double price;

    public static void main(String[] args) {
        Book book = new Book();
        book.setBookName("Java");
        book.setId(11);
        book.setPrice(24.2);
        System.out.println(book);

        Book book2 = new Book();
        book2.setId(12).setPrice(25.0).setBookName("Java 设计模式");
        System.out.println(book2);
    }
}
