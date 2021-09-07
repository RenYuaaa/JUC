package com.study.ren.juc.c_005;

/**
 * @author: renjiahui
 * @date: 2021-09-07 23:36
 * @description: 分析一下程序的输出：
 *  当run方法不加synchronized时，线程名是乱序的，count输出也是乱序的，也可能有重复数字
 *  当run方法加了synchronized是，线程名是乱序的，但count是倒序的
 */
public class T implements Runnable {

    private int count = 100;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
}
