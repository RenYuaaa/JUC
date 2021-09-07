package com.study.ren.juc.c_001;

/**
 * @author: renjiahui
 * @date: 2021-09-07 23:19
 * @description: synchronized关键字，对某个对象加锁
 */
public class T {

    private int count = 10;

    private Object object = new Object();

    public void m() {
        // 任何线程想要执行下面代码，必须先拿到object的锁
        synchronized (object) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

}
