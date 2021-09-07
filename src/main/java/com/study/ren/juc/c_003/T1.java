package com.study.ren.juc.c_003;

/**
 * @author: renjiahui
 * @date: 2021-09-07 23:33
 * @description:
 */
public class T1 {

    private int count = 10;

    public synchronized void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public void n() {
        count++;
    }

}
