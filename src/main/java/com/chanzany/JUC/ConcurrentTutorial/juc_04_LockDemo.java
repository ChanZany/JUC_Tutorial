package com.chanzany.JUC.ConcurrentTutorial;


import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("send Email");
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println("send SMS");
    }

    public void sayHello() throws Exception {
        System.out.println("sayHello");
    }
}

/**
 * 8 Lock
 * 1. 标准访问，请问先打印邮件还是短信（邮件）
 * 2. 邮件方法阻塞4s,请问先打印邮件还是短信（邮件）
 * 3. 新增普通sayHello方法，请问先打印邮件还是hello(hello)
 * 4. 两部手机，请问先打印邮件还是短信（短信）
 * 5. 两个静态同步方法，同一部手机，请问先打印邮件还是短信（邮件）
 * 6. 两个静态同步方法，两部手机，请问先打印邮件还是短信（邮件）
 * 7. 1个静态同步方法(邮件)，1个普通同步方法(短信)，同一部手机，请问先打印邮件还是短信（短信）
 * 8. 1个静态同步方法(邮件)，1个普通同步方法(短信)，两部手机，请问先打印邮件还是短信（短信）
 */
public class juc_04_LockDemo {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
//                phone.sendSMS();
//                phone.sayHello();
//                phone2.sendSMS();
//                phone.sendSMS();
                phone2.sendSMS();
//                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }

}

