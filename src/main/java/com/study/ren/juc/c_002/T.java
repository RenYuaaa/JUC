package com.study.ren.juc.c_002;

/**
 * @author: renjiahui
 * @date: 2021-09-07 23:30
 * @description: 对this加锁
 */
public class T {

    private int count = 10;

    public void m() {
        // 任何线程想要执行下面代码，必须先拿到object的锁
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}
