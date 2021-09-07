package com.study.ren.juc.c_004;

/**
 * @author: renjiahui
 * @date: 2021-09-07 23:34
 * @description:
 */
public class T {

    private static int count = 10;

    /**
     * 这里等同于synchronized(T.class)，相当于锁的是T对象
     */
    public synchronized static void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        // 考虑一下这里写synchronized(this)是否可以
        synchronized (T.class) {
            count--;
        }
    }
}
