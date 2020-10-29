package com.chanzany.interview_primary;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 请手写单例模式的实现
 * 要点：
 * 1. 该类在堆中只能存在一个实例     构造器私有化
 * 2. 该类必须自行创建这个实例       静态变量
 * 3. 它必须自行向整个系统提供这个实例   supplier方法
 * 4. 强调这是一个单例，可以用final修饰该实例
 * <p>
 * 饿汉式：直接创建对象，不管是否需要都会创建
 * <p>
 * 懒汉式：延迟创建对象，什么时候使用什么时候创建(restful 风格)
 * * 可能出现线程安全问题
 */

class StarvingSingleton {
    public static final StarvingSingleton INSTANCE = new StarvingSingleton();

    private StarvingSingleton() {
    }
}

class StarvingSingleton2 {
    public static final StarvingSingleton2 INSTANCE;
    private String username;
    private String password;

    static {
        //jdbc.....
        Properties properties = new Properties();
        try {
            properties.load(StarvingSingleton2.class.getClassLoader().getResourceAsStream("single.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        INSTANCE = new StarvingSingleton2(properties.getProperty("username"), properties.getProperty("password"));
    }

    private StarvingSingleton2(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "StarvingSingleton2{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new LazySingleton();
        }
        return instance;
    }
}

class LazySingleton2 {
    private static LazySingleton2 instance;
    private static Lock lock = new ReentrantLock();

    private LazySingleton2() {
    }

    public static LazySingleton2 getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                TimeUnit.MILLISECONDS.sleep(100);
                instance = new LazySingleton2();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return instance;
    }
}

/**
 * 在内部类被加载和初始化时，才创建INSTANCE实例对象
 * 静态内部类不会随着外部类的加载和初始化而初始化，它是要单独加载和初始化的
 * 因为是在内部类通过类加载器初始化对象，所以线程安全
 */
class LazySingleton3 {
    private LazySingleton3() {
    }
    private static class Inner {
        private final static LazySingleton3 INSTANCE = new LazySingleton3();
    }
    public static LazySingleton3 getInstance(){
        return Inner.INSTANCE;
    }
}

public class SingletonDemo {
    public static void main(String[] args) throws Exception {
//        StarvingSingleton2 instance = StarvingSingleton2.INSTANCE;
//        StarvingSingleton2 instance2 = StarvingSingleton2.INSTANCE;
//        System.out.println(instance == instance2);
//        System.out.println(instance);
        /*LazySingleton lazySingleton = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();
        System.out.println(lazySingleton==lazySingleton2);*/
        Callable<LazySingleton3> callable = LazySingleton3::getInstance;
        ExecutorService threadPool = new ThreadPoolExecutor(
                2, 5, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        Future<LazySingleton3> f1 = threadPool.submit(callable);
        Future<LazySingleton3> f2 = threadPool.submit(callable);
        LazySingleton3 lazySingleton1 = f1.get();
        LazySingleton3 lazySingleton2 = f2.get();
        System.out.println(lazySingleton1 == lazySingleton2);
        threadPool.shutdown();
    }
}
