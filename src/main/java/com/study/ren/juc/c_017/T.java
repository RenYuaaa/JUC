package com.study.ren.juc.c_017;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象object，如果object的属性发生改变，不影响锁的使用
 * 但是如果object变成另一个对象，则锁定的对象发生改变
 * 应该避免将锁定对象的引用变成另外的对象
 */
public class T {

    /*final*/ Object object = new Object();

    void m() {
        synchronized(object) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();

        // 启动第一个线程
        new Thread(t::m, "t1").start();

        // 创建第二个线程
        Thread t2 = new Thread(t::m, "t2");

        // 锁对象发生改变，所以t2线程得以执行，如果注释掉这句话，线程2将永远得不到执行机会
        t.object = new Object();
        t2.start();
    }
}
